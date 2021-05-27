#!/usr/bin/python
# -*- coding: utf-8 -*-

from flask import *
import json
import datetime
from modeles import modeleGSBRV

app = Flask( __name__ )


@app.route( '/' , methods = [ 'GET' ] )
def accueil() :

	return "Hello"
	


@app.route( '/visiteurs/<matricule>.<mdp>' , methods = [ 'GET' ] )
def seConnecter( matricule , mdp ) :
	visiteur = modeleGSBRV.seConnecter( matricule , mdp )
	
	if visiteur != None and len( visiteur ) != 0 :
		reponse = make_response( json.dumps( visiteur ) )
		reponse.mimetype = 'application/json'
		reponse.status_code = 200
	else :
		visiteur = {}
		reponse = make_response( json.dumps(visiteur) )
		reponse.mimetype = 'application/json'
		reponse.status_code = 200
	return reponse
		
        
@app.route( '/rapports/<matricule>/<mois>/<annee>' , methods = [ 'GET' ] )
def getRapportsVisite( matricule , mois , annee ) :
	
	
	rapports = modeleGSBRV.getRapportsVisite( matricule , mois , annee )
	
	if rapports != None :
		reponse = make_response( json.dumps( rapports).encode('utf8') )
		reponse.mimetype = 'application/json'
		reponse.status_code = 200
	else :
		reponse = make_response( '' )
		reponse.mimetype = 'application/json'
		reponse.status_code = 404
	return reponse
	

@app.route( '/rapports/echantillons/<matricule>/<numRapport>' , methods = [ 'GET' ] )
def getEchantillonsOfferts( matricule , numRapport ) :
	offres = modeleGSBRV.getEchantillonsOfferts( matricule , numRapport )

	
	if offres != None :
		reponse = make_response( json.dumps( offres ) )
		reponse.mimetype = 'application/json'
		reponse.status_code = 200
	else :
		reponse = make_response( '' )
		reponse.mimetype = 'application/json'
		reponse.status_code = 404
	return reponse

	
@app.route( '/praticiens' , methods = [ 'GET' ] )
def getPraticiens() :
	praticiens = modeleGSBRV.getPraticiens()
	
	reponse = make_response( json.dumps( praticiens ) )
	reponse.mimetype = 'application/json'
	reponse.status_code = 200
	
	return reponse
	
@app.route( '/motifs' , methods = [ 'GET' ] )
def getMotifs() :
	motifs = modeleGSBRV.getMotifs()
	
	reponse = make_response( json.dumps( motifs ) )
	reponse.mimetype = 'application/json'
	reponse.status_code = 200
	
	return reponse
	
	
@app.route( '/medicaments' , methods = [ 'GET' ] )
def getMedicaments() :
	medicaments = modeleGSBRV.getMedicaments()
	
	if medicaments != None :
		reponse = make_response( json.dumps( medicaments ) )
		reponse.mimetype = 'application/json'
		reponse.status_code = 200
	else :
		reponse = make_response( '' )
		reponse.mimetype = 'application/json'
		reponse.status_code = 404
	return reponse
	
@app.route( '/rapports' , methods = [ 'POST' ] )
def addRapportVisite() :
	unRapport = request.form['rapport'] # rapport fait appel a la clef GSON que l'on envoi
	rapport = json.loads(unRapport)
	#{"bilan":"très bien","coefConfiance":4,"dateRedaction":"2021-5-24","dateVisite":"2021-5-13","matricule":"b16","numMotif":4,"numPraticien":5}
	
	insert = modeleGSBRV.enregistrerRapportVisite( 	rapport[ 'matricule' ] , 
																rapport[ 'numPraticien' ] , #ce sont les champs déclaré dans la classe java
																rapport[ 'dateVisite' ] ,
																rapport[ 'bilan' ],
																rapport[ 'coefConfiance' ],
																rapport[ 'numMotif' ],
																rapport[ 'dateRedaction' ])
																
															
	print("================", rapport)
												
	return Response(rapport, status=200, mimetype='application/json')
	

@app.route( '/nouveauMdp' , methods = [ 'POST' ] )
def modifierMdp() :
	data = request.form['data']
	data = json.loads(data)
	
	
	insert = modeleGSBRV.modifierMdp( data[ 'matricule' ],data[ 'confirmerMdp' ])
																
															
	print("================", data)
												
	return Response(data, status=200, mimetype='application/json')
	

@app.route( '/rapports/echantillons/<matricule>/<numRapport>' , methods = [ 'POST' ] )
def addEchantillonsOfferts( matricule , numRapport ) :
	echantillons = json.loads( request.data.decode('utf-8') )
	nbEchantillons = modeleGSBRV.enregistrerEchantillonsOfferts( matricule , numRapport , echantillons )
	
	
	reponse = make_response( '' )												
	if numRapport != None :
		reponse.headers[ 'Location' ] = '/rapports/echantillons/%s/%s' % ( matricule , numRapport )
		reponse.status_code = 201
	else :
		reponse.status_code = 409
	return reponse






if __name__ == '__main__' :

	app.run(debug=True, host="192.168.2.237", port=5000)



#!/usr/bin/python
# -*- coding: utf-8 -*-


import mysql.connector


connexionBD = None

def getConnexionBD() :
	global connexionBD
	try :
		if connexionBD == None :
			connexionBD = mysql.connector.connect(
					host = 'localhost' ,
					user = 'root' ,
					password = 'azerty' ,
					database = 'gsbrv'
				)
		print('=============', connexionBD)
		return connexionBD
	except Exception as e :
		print('==================', e)
		return None


def seConnecter( matricule , mdp ) :
	try :
		curseur = getConnexionBD().cursor()
		requete = '''
					select vis_nom , vis_prenom, vis_ville, vis_adresse, vis_cp
					from Visiteur
					inner join Travailler as t1
					on t1.vis_matricule = Visiteur.vis_matricule
					where t1.jjmmaa = (
						select MAX(t2.jjmmaa) 
						from Travailler as t2 
						where t2.vis_matricule = t1.vis_matricule
					) 
					and t1.tra_role <> 'Responsable'
					and Visiteur.vis_matricule = %s
					and Visiteur.vis_mdp = %s
				'''

		curseur.execute( requete , ( matricule , mdp) )
		
		enregistrement = curseur.fetchone()
		
		visiteur = {}
		if enregistrement != None :
			visiteur[ 'vis_matricule' ] = matricule
			visiteur[ 'vis_nom' ] = enregistrement[ 0 ]
			visiteur[ 'vis_prenom' ] = enregistrement[ 1 ]
			visiteur[ 'vis_ville' ] = enregistrement[ 2 ]
			visiteur[ 'vis_adresse' ] = enregistrement[ 3 ]
			visiteur[ 'vis_cp' ] = enregistrement[ 4 ]
			
		curseur.close()
		return visiteur
		
	except :
		return None
		
def getRapportsVisite( matricule , mois , annee ) :
	try :
		curseur = getConnexionBD().cursor()
		requete = '''
					select *
					from RapportVisite as rv
					inner join Praticien as p
					on p.pra_num = rv.pra_num
					inner join Visiteur as v
					on rv.vis_matricule = v.vis_matricule
					inner join Motif as m
					on rv.numero_motif = m.numero_motif
					where rv.vis_matricule = %s
					and MONTH(rv.rap_date_visite) = %s
					and YEAR(rv.rap_date_visite) = %s
					order by rv.rap_date_visite
				'''
		
		curseur.execute( requete , ( matricule , mois , annee ) )
		num_fields = len(curseur.description)
		colonnes = [i[0] for i in curseur.description]
		enregistrement = curseur.fetchall()
		data = []
		for enrg in enregistrement:
			enrg = list(enrg)
			res = {colonnes[i]: str(enrg[i]) for i in range(len(enrg))}
			data.append(res)
		
			
		curseur.close()
		print(data)
		return data
		
	except Exception as e:
		print(e)
		return None
		
		
def setRapportLu(numRapportVisite):
	
	try :
		connexion = getConnexionBD()
		curseur = connexion.cursor()
		
		requeteRapportLu = '''
							update RapportVisite
							set rap_lu = 1
							where rap_num = %s;
						''' % (numRapportVisite)
		curseur.execute( requeteRapportLu)
		connexion.commit()
		curseur.close()
		
		return 1
		
	except Exception as e:
		print("$========>", e)
		return None
		
	
		
def getEchantillonsOfferts( matricule , numRapportVisite ) :
	
	try :
		curseur = getConnexionBD().cursor()
		
		setRapportLu(numRapportVisite)
						
		requete = '''
					select med_nomcommercial , off_quantite, med_effets, med_prixechantillon 
					from Offrir as o 
					inner join Medicament as m 
					on m.med_depotlegal = o.med_depotlegal 
					where o.vis_matricule = %s 
					and o.rap_num = %s;
				'''
				
		

		curseur.execute( requete , ( matricule , numRapportVisite ) )
		
		enregistrements = curseur.fetchall()
		
		offres = []
		for unEnregistrement in enregistrements :
			uneOffre = {}
			uneOffre[ 'med_nomcommercial' ] = unEnregistrement[ 0 ]
			uneOffre[ 'off_quantite' ] = unEnregistrement[ 1 ]
			uneOffre[ 'med_effets' ] = unEnregistrement[ 2 ]
			uneOffre[ 'med_prixechantillon' ] = unEnregistrement[ 3]
			offres.append( uneOffre )
			
		curseur.close()
		return offres
	
	except :
		return None

		
def getPraticiens() :
	
	try :
		curseur = getConnexionBD().cursor()
		requete = '''
					select pra_num , pra_nom , pra_prenom , pra_ville
					from Praticien
				'''
		
		curseur.execute( requete , () )
		
		enregistrements = curseur.fetchall()
		
		praticiens = []
		for unEnregistrement in enregistrements :
			unPraticien = {}
			unPraticien[ 'pra_num' ] = unEnregistrement[ 0 ]
			unPraticien[ 'pra_nom' ] = unEnregistrement[ 1 ]
			unPraticien[ 'pra_prenom' ] = unEnregistrement[ 2 ]
			unPraticien[ 'pra_ville' ] = unEnregistrement[ 3 ]
			praticiens.append( unPraticien )
			
		curseur.close()
		return praticiens
		
	except Exception as e :
		print(e)
		return None

def getMotifs() :
	
	try :
		curseur = getConnexionBD().cursor()
		requete = '''
					select *
					from Motif
				'''
		
		curseur.execute( requete , () )
		
		enregistrements = curseur.fetchall()
		
		motifs = []
		for unEnregistrement in enregistrements :
			unMotif = {}
			unMotif[ 'numero_motif' ] = unEnregistrement[ 0 ]
			unMotif[ 'libelle_motif' ] = unEnregistrement[ 1 ]
			
			motifs.append( unMotif )
			
		curseur.close()
		return motifs
		
	except Exception as e :
		print(e)
		return None

def getMedicaments() :
	
	try :
		curseur = getConnexionBD().cursor()
		requete = '''
					select med_depotlegal , med_nomcommercial
					from Medicament
				'''
		
		curseur.execute( requete , () )
		
		enregistrements = curseur.fetchall()
		
		medicaments = []
		for unEnregistrement in enregistrements :
			unMedicament = {}
			unMedicament[ 'med_depotlegal' ] = unEnregistrement[ 0 ]
			unMedicament[ 'med_nomcommercial' ] = unEnregistrement[ 1 ]
			medicaments.append( unMedicament )
			
		curseur.close()
		return medicaments
		
	except :
		return None


	

def genererNumeroRapportVisite( matricule ) :
	
	try :
		curseur = getConnexionBD().cursor()
		requete = '''
					select max(rap_num)
					from RapportVisite
					where vis_matricule = %s
				'''

		curseur.execute( requete , ( matricule , ) )
		
		enregistrement = curseur.fetchone()

		if enregistrement[ 0 ] != None :
			return enregistrement[ 0 ] + 1
		else :
			return 1
			
		curseur.close()
		return visiteur
		
	except :
		return None



def enregistrerRapportVisite( matricule , numPraticien , dateVisite , bilan, coefConfiance, motif, dateRedaction ) :
	
	
		try:
			curseur = getConnexionBD().cursor()

			requete = '''
				INSERT INTO RapportVisite (vis_matricule, rap_date_visite, rap_bilan, pra_num, rap_coef_confiance, rap_lu, numero_motif, date_redaction)
				values( %s , %s , %s , %s , %s, %s, %s, %s )
				'''

			curseur.execute( requete, ( matricule , dateVisite , bilan , numPraticien, coefConfiance, 0 ,motif, dateRedaction ) )
			connexionBD.commit()
			curseur.close()

			return 1

		except:
			return None


def modifierMdp( matricule ,mdp ) :
	
	
		try:
			curseur = getConnexionBD().cursor()

			requete = '''
			
				UPDATE Visiteur set vis_mdp = %s
				where vis_matricule = %s
				
				'''

			curseur.execute( requete, ( mdp, matricule ) )
			connexionBD.commit()
			curseur.close()

			return 1

		except:
			return None	
		
def enregistrerEchantillonsOfferts( matricule , numRapport , echantillons ) :
	
	try:
		curseur = getConnexionBD().cursor()
		
		requete = '''
			insert into Offrir( vis_matricule , rap_num , med_depotlegal , off_quantite )
			values( %s , %s , %s , %s )
			'''
			
		nbOffresInserees = 0
		for offre in echantillons.items() :
			curseur.execute( requete, ( matricule , numRapport , offre[ 0 ] , offre[ 1 ]) )
			nbOffresInserees += curseur.rowcount
			
		connexionBD.commit()
		
		curseur.close()

		return nbOffresInserees

	except :
		return None

		
if __name__ == '__main__' :
		print ('Authentification du visiteur a131 :')
		print (seConnecter( 'a131' , '' ))
		print('')
		
		print('Liste des rapports de visite du visiteur a131 :')
		for unRapport in getRapportsVisite( 'a131' , 4 , 2018 ) :
			print (unRapport)
		print('')
		
		print('Liste des praticiens :')
		for unPraticien in getPraticiens() :
			print(unPraticien)
		print('')
		
		print('Liste des medicaments :')
		for unMedicament in getMedicaments() :
			print (unMedicament)
		print
		
		print ('Générer numero rapport pour le visiteur a131 :')
		print (genererNumeroRapportVisite( 'a131' ))
		print('')
		'''
		print 'Générer numero rapport pour le visiteur t60 :'
		print genererNumeroRapportVisite( 't60' )
		print
		
		print 'Enregistrer un rapport de visite pour le visiteur a131 :'
		print enregistrerRapportVisite( 'a131' , 85 , '2018-07-01' , 'RAS' )
		print
		
		echantillons = {}
		echantillons[ 'EVILR7' ] = 2 ;
		echantillons[ 'PHYSOI8' ] = 1 ;
		print echantillons
		
		print 'Enregistrer les echantillons offerts par le visiteur a131 lors de sa 1ère visite :'
		print enregistrerEchantillonsOfferts( 'a131' , 1 , echantillons )
		print
		'''
		
		print('Liste des medicaments offerts par le visiteur a131 lors de sa 1ère visite :')
		for uneOffre in getEchantillonsOfferts( 'a131' , 1 ) :
			print(uneOffre)
		print('')
		
		

package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	
	public Village(String nom, int nbVillageoisMaximum) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	private static class Marche {
        private Etal[] etals;

        private Marche(int numberOfEtals) {
            etals = new Etal[numberOfEtals];
            for (int i = 0; i < numberOfEtals; i++) {
                etals[i] = new Etal();
            }
        }
        
        private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
            if (indiceEtal >= 0 && indiceEtal < etals.length) {
                etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit);
            } else {
                System.out.println("Indice d'étal invalide.");
            }
        }  
        
        private int trouverEtalLibre() {
            for (int i = 0; i < etals.length; i++) {
                if (!etals[i].isEtalOccupe()) {
                    return i;
                }
            }
            return -1;
        }
        
        private  Etal[] trouverEtals(String produit) {
        	
        	int count = 0;
            Etal[] etalsAvecProduit = new Etal[count];

        	
        	for (int i=0; i< etals.length; i++) {
        		if (etals[i].contientProduit(produit))
        		{
        			count++;
                    etalsAvecProduit[count] = etals[i];
        		}
        	}
        	
        return etalsAvecProduit;
        	
        }
        
        private Etal trouverVendeur(Gaulois gaulois) {
            for (Etal etal : etals) {
                if (etal != null && etal.getVendeur() == gaulois) {
                    return etal;
                }
            }
            return null;
        }
        
        public String afficherMarche() {
            StringBuilder affichage = new StringBuilder();
            int nbEtalVide = 0;

            for (int i=0; i< etals.length; i++) {
                if (etals[i].isEtalOccupe()) {
                    affichage.append(etals[i].afficherEtal());
                } else {
                    nbEtalVide++;
                }
            }

            if (nbEtalVide > 0) {
                affichage.append("Il reste ").append(nbEtalVide).append(" étals non utilisés dans le marché.\n");
            }

            return affichage.toString();
        }
        
	}
}
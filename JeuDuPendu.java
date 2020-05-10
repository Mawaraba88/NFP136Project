package tp;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
public class JeuDuPendu {

	public static void main(String[] args) throws IOException  {
		// TODO Auto-generated method stub

		// Lecture du fichier de texte
		// Le fichier contient 22743 lignes
		int nbr = 22743;
		String [] data = new String[nbr];

		try {
			FileReader fichier = new FileReader("src/tp/liste_mots-2.txt");
			BufferedReader textData = new BufferedReader(fichier); 

			int i;
			for(i=0;i<data.length;i++) {
				data[i] = textData.readLine();
			
			}
			textData.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

		// L'entête de l'application
		System.out.println("-----------------------JEU DU PENDU--------------");
		System.out.println("Principe:\n"
				+ "Le jeu consiste à déviner le mot affiché.\n"
				+ "Vous avez droit à 6 erreurs maximum.\n"
				+ "Au delà, vous serez pendu.\n"
				+ "Vous pouvez jouer une nouvelle partie ou quitter le programme. \n"
				+ "Bonne chance!!! ");

		System.out.println("--------------------------------------------------");
		
		
		// Initialisation des variables
		int nbre;
		boolean gagne = false;
		char reponse = ' ';
		char c = ' ';
		boolean ok;
		String motEssaye ="";
		String motAdeviner;
		int  NbErrMax =0;// Le nbre d'erreur à incrémenter.
		int fromIndex;
		String lettre;
		StringBuilder motTempo;
		StringBuilder buf ;
		String motAfficher;
		Scanner sc = new Scanner(System.in);
		
		do {// La boucle principale: tant que la reponse est 'O', traite toutes les instructions.
			
			// Choix aléatoire du mot à deviner.
			nbre = (int)( nbr * Math.random());
			motAdeviner = data[nbre];
		
			// Créons un string "*" qui a la même longueur que le mot à  déviner
			 buf = new StringBuilder();

			for(int j=0; j<motAdeviner.length(); j++) {
				buf.append("*");
			}
			motAfficher = buf.toString();

			System.out.println("Le mot à deviner est: " + motAfficher);


			// Créons une boucle principale: tantque le nbre d'erreur est inf à 6  et qu'on a pas 
			
			while((gagne==false)&& (NbErrMax<6)) {
				// On lit le caractère du joueur et on le controle
				
				 ok = false;
				while(ok==false) {
					System.out.println("Saisir une lettre:");

					 lettre = sc.nextLine();
					if(!lettre.equals("")) {
						
						// On convertit lettre en char
						
						c= lettre.charAt(0);
						if(Character.isLetter(c)|| c==('*')) {// On détermine si le caractère est une lettre ou un ('-')

							if(motEssaye.indexOf(c)==-1) {
								ok=true;
								motEssaye=motEssaye+c;

							}


						}
					}
				}

				// On teste si le caractère proposé est dans le mot à deviner
				
				if(motAdeviner.indexOf(c)==-1) {
					
					// Le caractère n'est pas dans le mot à deviner et donc on incrémente le nombre d'erreur.
					NbErrMax++;
				}
				else {
					// Le caractère est dans le mot à deviner
					
					motTempo = new StringBuilder(motAfficher);
					fromIndex = motAdeviner.indexOf(c);
					// On remplace les "*" par les lettres proposées
					while(fromIndex!=-1) {
						motTempo.setCharAt(fromIndex, c);
						fromIndex++;
						fromIndex = motAdeviner.indexOf(c, fromIndex);
					}
					motAfficher= motTempo.toString();
				}

				System.out.println(motAfficher);
				System.out.println("Lettre(s) déja proposée(s): "+ motEssaye);
				if(motAfficher.equals(motAdeviner)) {
					gagne = true;
				}
			}	
			// On teste si on a gagné ou perdu
			if(gagne) {
				System.out.println("Bravo!\n");
				System.out.println("Le mot à deviner était bien :\n " + motAdeviner);
			}
			else {
				System.out.println("Perdu!\n");
				System.out.println("Vous avez atteint le nombre maximum d'erreur.\n");
				System.out.println("Le mot à deviner était: "+motAdeviner);

			}
			 
			// On invite le joueur à rejouer ou à quitter le programme
			do {
				NbErrMax = 0;
				gagne = false;
				motEssaye = "";
				System.out.println("Voulez-vous jouer une nouvelle partie ? (O/N)");
				reponse=sc.nextLine().charAt(0);
				if(reponse!='O' && reponse !='N') {
					System.out.println("Désolé votre choix est invalide. Veuillez saisir O/N ");
				}
				
			}while(reponse !='O' && reponse !='N');
			
		}while(reponse=='O');
		System.out.println("Aurevoir!");
	}
}

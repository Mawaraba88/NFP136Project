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

		// L'ent�te de l'application
		System.out.println("-----------------------JEU DU PENDU--------------");
		System.out.println("Principe:\n"
				+ "Le jeu consiste � d�viner le mot affich�.\n"
				+ "Vous avez droit � 6 erreurs maximum.\n"
				+ "Au del�, vous serez pendu.\n"
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
		int  NbErrMax =0;// Le nbre d'erreur � incr�menter.
		int fromIndex;
		String lettre;
		StringBuilder motTempo;
		StringBuilder buf ;
		String motAfficher;
		Scanner sc = new Scanner(System.in);
		
		do {// La boucle principale: tant que la reponse est 'O', traite toutes les instructions.
			
			// Choix al�atoire du mot � deviner.
			nbre = (int)( nbr * Math.random());
			motAdeviner = data[nbre];
		
			// Cr�ons un string "*" qui a la m�me longueur que le mot �  d�viner
			 buf = new StringBuilder();

			for(int j=0; j<motAdeviner.length(); j++) {
				buf.append("*");
			}
			motAfficher = buf.toString();

			System.out.println("Le mot � deviner est: " + motAfficher);


			// Cr�ons une boucle principale: tantque le nbre d'erreur est inf � 6  et qu'on a pas 
			
			while((gagne==false)&& (NbErrMax<6)) {
				// On lit le caract�re du joueur et on le controle
				
				 ok = false;
				while(ok==false) {
					System.out.println("Saisir une lettre:");

					 lettre = sc.nextLine();
					if(!lettre.equals("")) {
						
						// On convertit lettre en char
						
						c= lettre.charAt(0);
						if(Character.isLetter(c)|| c==('*')) {// On d�termine si le caract�re est une lettre ou un ('-')

							if(motEssaye.indexOf(c)==-1) {
								ok=true;
								motEssaye=motEssaye+c;

							}


						}
					}
				}

				// On teste si le caract�re propos� est dans le mot � deviner
				
				if(motAdeviner.indexOf(c)==-1) {
					
					// Le caract�re n'est pas dans le mot � deviner et donc on incr�mente le nombre d'erreur.
					NbErrMax++;
				}
				else {
					// Le caract�re est dans le mot � deviner
					
					motTempo = new StringBuilder(motAfficher);
					fromIndex = motAdeviner.indexOf(c);
					// On remplace les "*" par les lettres propos�es
					while(fromIndex!=-1) {
						motTempo.setCharAt(fromIndex, c);
						fromIndex++;
						fromIndex = motAdeviner.indexOf(c, fromIndex);
					}
					motAfficher= motTempo.toString();
				}

				System.out.println(motAfficher);
				System.out.println("Lettre(s) d�ja propos�e(s): "+ motEssaye);
				if(motAfficher.equals(motAdeviner)) {
					gagne = true;
				}
			}	
			// On teste si on a gagn� ou perdu
			if(gagne) {
				System.out.println("Bravo!\n");
				System.out.println("Le mot � deviner �tait bien :\n " + motAdeviner);
			}
			else {
				System.out.println("Perdu!\n");
				System.out.println("Vous avez atteint le nombre maximum d'erreur.\n");
				System.out.println("Le mot � deviner �tait: "+motAdeviner);

			}
			 
			// On invite le joueur � rejouer ou � quitter le programme
			do {
				NbErrMax = 0;
				gagne = false;
				motEssaye = "";
				System.out.println("Voulez-vous jouer une nouvelle partie ? (O/N)");
				reponse=sc.nextLine().charAt(0);
				if(reponse!='O' && reponse !='N') {
					System.out.println("D�sol� votre choix est invalide. Veuillez saisir O/N ");
				}
				
			}while(reponse !='O' && reponse !='N');
			
		}while(reponse=='O');
		System.out.println("Aurevoir!");
	}
}

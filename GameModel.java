package com.igs.ipi.tpspringbootCHARTOIRE.Model;

public class GameModel
{
	
	private String Name1;
	private String Name2;
	
	private boolean tourJoueur;
	//Si tourJoueur = 0, c'est au tour de Name1 de jouer.
	private boolean qui = (boolean) getRandomIntInclusive();
	
	////////////// Attributs pour la grille//////////////
	private int[][] jetons = new int[HAUTEUR][LARGEUR];
	private static final int HAUTEUR = 6;
	private static final int LARGEUR = 7;
	private static final int NB_JETON_TOWIN = 4;
	
	
	
	private int numJoueurGagnant = 0;
	// A 0 la partie n'est soit pas commencée soit pas terminée.
	
	
	public GameModel(String Name1, String Name2)
	{
		this.Name1 = "Bobby Lapointe" ;
		this.Name2 = "David Bowie";
	}
	
	public String getName1(String Name1)
	{
		return Name1;
	}

	public void setName1(String name1)
	{
		this.Name1 = name1;
	}
	
	public String getName2(String Name2)
	{
		return Name2;
	}

	public void setName2(String name2)
	{
		this.Name2 = name2;
	}
	//Aléatoire pour savoir qui commence
	public Object getRandomIntInclusive()
	{
		  double min = Math.ceil(0);
		  double max = Math.floor(1);
		  return (Math.floor (Math.random() * (max - min +1)) + min);
	}
	
	public void quiCommence(boolean qui)
	{
		if (tourJoueur == qui)
		{
			System.out.println ("C'est Name1 qui débute la partie");
		}
		else
		{
			System.out.println ("C'est Name2 qui débute la partie");
		}
		
	}
	
	 
	 public void addJeton(Integer index)
	 {
			// verification si partie terminée 
			if (numJoueurGagnant != 0)
			{
				throw new IllegalStateException("La partie est terminée. Si vous le voulez vous pouvez en recommencer une.");
			}
			// h=0 est la ligne du haut, on va itérer sur la hauteur en sens inverse pour chercher une place libre en bas
			for (int h = jetons.length - 1; h >= 0; h--)
			{
				if (jetons[h][index] == 0)
				{
	
					if (tourJoueur == true)
					{
						jetons[h][index] = 1;
						tourJoueur = false;
						
					}
					else if (tourJoueur == false)
					{
						jetons[h][index] = 2;
						tourJoueur = true; 
					}
					else
					{
						System.out.println("Erreur");
					}
					checkHorizontal();
					checkVertical();
					checkDiagonalDroiteGauche();  
					checkDiagonalGaucheDroite();
					return;
				}
			}
			throw new IllegalArgumentException ("Désolé, la colonne " + index + " est remplie. Veuillez en choisir une autre.");
	}
	 
	 
     	// i = hauteur
		// verification horizontale
		public int checkHorizontal()
		{

			int colorjetonprecedent = 0; 
			int colorjetonencours = 0;
			int count = 0;
			// On parcourt les lignes
			for (int i = 0; i < jetons.length; i++)
			{
	        	count = 0;    	
	        	colorjetonprecedent = jetons[i][0];
	        	//j = largeur
	        	// On parcourt les colonnes pour chaque ligne
	            for (int j = 0; j < jetons[i].length; j++)
	            {
	            	// on ne verifie que le cas où il y a un jeton dans la case
	            	if (jetons[i][j] != 0)
	            	{   		
	            		colorjetonencours = jetons[i][j];
	            		if (colorjetonprecedent == colorjetonencours)
	            		//Si la couleur de la case précédente est la même que la case en cours
	            		{
	            			count = count + 1; 
	            		
	            			if (count == 4 && colorjetonprecedent != 0)
	            			{
	            				return this.numJoueurGagnant = colorjetonprecedent; 
	            			}
	            		} 
	            		else
	            		{
	            			count = 1;
	            			colorjetonprecedent = colorjetonencours;
	            		}
	                }
	            }
	        }
	        return numJoueurGagnant;
		}
		


		// verification verticale 
		public int checkVertical()
		{
			int colorjetonprecedent = 0; 
			int colorjetonencours = 0;
			int count = 0; 
	        for (int i = 0; i < jetons[0].length; i++)
	        {   
	        	count = 0;    	
	        	colorjetonprecedent = jetons[0][i];
	            for (int j = 0; j < jetons.length; j++)
	            {
	            	// on ne verifie que le cas où il y a un jeton dans la case
	            	if (jetons[j][i] != 0)
	            	{   		
	            		colorjetonencours = jetons[j][i];
	            		if (colorjetonprecedent == colorjetonencours)
	            		{
	            			count = count + 1; 
	            			
	            			if (count == 4 && colorjetonprecedent != 0)
	            			{
	            				return this.numJoueurGagnant = colorjetonprecedent; 
	            			}
	            		} 
	            		else
	            		{
	            			count = 1;
	            			colorjetonprecedent = colorjetonencours;
	            		}
	                }
	            }
	        }
	        return numJoueurGagnant;
		}
		
		// verification diagonale droite-gauche
			public int checkDiagonalDroiteGauche()
			{
				int colorjetonprecedent = 0; 
				int colorjetonencours = 0;
				int count=0; 
		        for (int i = 0; i < jetons.length - NB_JETON_TOWIN + 1; i++)
		        {	
		            for (int j = 0; j < jetons[i].length - NB_JETON_TOWIN + 1; j++)
		            {
		            	
		            	if (jetons[i][j] != 0)
		            	{  
		            		colorjetonprecedent = jetons[i][j];
		            		count=1; 
		            		
		            		for (int k = 1; k < NB_JETON_TOWIN; k++)
		            		{
		            			colorjetonencours = jetons[i+k][j+k];
		            			if (colorjetonprecedent == colorjetonencours)
		            			{
		            				count = count + 1; 
		            				System.out.println(count);
		            				if (count == 4 && colorjetonprecedent != 0)
		            				{
		            					return this.numJoueurGagnant = colorjetonprecedent; 
		            				}
		            			} 
		            			else
		            			{
		            				count = 1;
		            				colorjetonprecedent = colorjetonencours;
		            			}
		            		}
		                }
		            }
		        }
		        return numJoueurGagnant;
			}
			
		// verification diagonale gauche-droite
			public int checkDiagonalGaucheDroite()
			{
				int colorjetonprecedent = 0; 
				int colorjetonencours = 0;
				int count = 0; 
				for (int i = 0; i < jetons.length - NB_JETON_TOWIN + 1; i++)
				{		
					for (int j = jetons[i].length - 1; j >= NB_JETON_TOWIN - 1; j--)
					{
						if (jetons[i][j] != 0)
						{  
							colorjetonprecedent = jetons[i][j];
							count = 1; 
							for (int k = 1; k < NB_JETON_TOWIN ; k++)
							{ 
								colorjetonencours = jetons[i+k][j-k];
								if (colorjetonprecedent == colorjetonencours)
								{
									count = count + 1; 
									if (count == 4 && colorjetonprecedent != 0)
									{
										return this.numJoueurGagnant = colorjetonprecedent; 
									}
								} 
								else
								{
									count = 1;
									colorjetonprecedent = colorjetonencours;
								}
							}
						}
					}
				}
				return numJoueurGagnant;
			}

	}
	 
		


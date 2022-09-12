package UCN;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class App
{
	public static void main(String[]args) throws FileNotFoundException
	{
		//vectores que guardan los datos de los aliens
		String[] nameSpecies = new String[1000];
		String[] nameAliens = new String[1000];
		String[] homePlanet = new String[1000];
		String[] type = new String[1000];
		String[] universalIdentify = new String[1000];
		double[] alienAge = new double[1000];
		double[] alienHeight = new double[1000];
		double[] alienWeight = new double[1000];
		int alienCounter = 0;
		
		//vectores que guardan los datos de los humanos
		String[] nationality = new String[1000];
		String[] name = new String[1000];
		String[] region = new String[1000];
		String[] city = new String[1000];
		String[] planet = new String[1000];
		double[] identify = new double[1000];
		double[] age = new double[1000];
		double[] height = new double[1000];
		double[] weight = new double[1000];
		int humanCounter = 0;
		
		String[] uniqueP = new String[500];
		int[] workers = new int[500];
		
		alienCounter = readX(nameSpecies, nameAliens, homePlanet, type, universalIdentify, alienAge, alienHeight, alienWeight, uniqueP);
		humanCounter = readH(nationality, name, region, city, planet, identify, age,height, weight, uniqueP, workers);
		
		/*
		 * Esto es para comprobar que esta funcionado correctamente :)
		 */
		int a = 0;
		while(uniqueP[a]!=null)
		{
			System.out.println("Planeta: "+uniqueP[a]+" trabajadores: "+workers[a]);
			a++;
		}
		for(int i=0;i<alienCounter;i++)
		{
			System.out.println("Alien("+(i+1)+"): "+nameAliens[i]);
		}
		for(int i=0;i<humanCounter;i++)
		{
			System.out.println("Humano("+(i+1)+"): "+name[i]);
		}
		
		System.out.println(translator("Hola"));
		/*
		 * Fin
		 */
	}
	
	
	public static int readX(String[] nameSpecies, String[] nameAliens, String[] homePlanet, String[] type, String[] universalIdentify, double[] alienAge, double[] alienHeight, double[] alienWeight, String[] uniqueP) throws FileNotFoundException
	{
		Scanner arch = new Scanner(new File("x.txt"));
		int count = 0;
		
		while(arch.hasNext())
		{
			String[] parts = arch.nextLine().split(",");
			nameSpecies[count] = parts[0];
			nameAliens[count] = translator(parts[1]);
			universalIdentify[count] = parts[2];
			homePlanet[count] = parts[3];
			alienAge[count] = Double.valueOf(parts[4]);
			alienHeight[count] = Double.valueOf(parts[5]);
			alienWeight[count] = Double.valueOf(parts[6]);
			type[count] = parts[7];
			uniquePlanet(uniqueP, parts[3]);
			
			count++;
			
		}
		return count;
	}

	public static int readH(String[] nationality, String[] name, String[] region, String[] city, String[] planet, double[] identify, double[] age, double[] height, double[] weight, String[] uniqueP, int[] workers) throws FileNotFoundException
	{
		Scanner arch = new Scanner(new File("h.txt"));
		int count = 0;
		String[] planets;
		
		while(arch.hasNext())
		{
			String[] parts = arch.nextLine().split(",");
			nationality[count] = parts[0];
			name[count] = parts[1];
			identify[count] = Double.valueOf(parts[2]);
			region[count] = parts[3];
			city[count] = parts[4];
			age[count] = Double.valueOf(parts[5]);
			height[count] = Double.valueOf(parts[6]);
			weight[count] = Double.valueOf(parts[7]);
			planet[count] = parts[8];
			//separa los planetas en los que trabaja el humano
			planets = parts[8].split("/");
			//este for suma uno a la posicion de trabajadores del planeta, si el planeta en el que trabaja el humano no existe lo agraga a la lista y suma uno al la lista de trabajadores
			for(int a=0;a<planets.length;a++)
			{
				workers[uniquePlanet(uniqueP, planets[a])]+=1;;
			}
			count++;
		}
		return count;
	}
	
	/*
	 * uniquePlanet
	 * compueba si el planeta existe dentro de la lista planetas unicos
	 * si existe devuelve la posicion en la lista
	 * si no existe lo agraga y devuelve la posicion en la que lo agrago
	 */
	private static int uniquePlanet(String[] uniqueP, String nameP)
	{
		int a = 0;
		while(uniqueP[a]!=null)
		{
			if(uniqueP[a].equals(nameP))
			{
				break;
			}
			a++;
		}
		if(uniqueP[a]==null)
		{
			uniqueP[a]=nameP;
			return a;
		}
		else {return a;}
	}
	/*
	 * un traductor bidireccional
	 */
	public static String translator(String word)
	{
		String[] charList = word.split("");
		String returnWord="";
		for(int a=0;a<charList.length;a++)
		{
			switch(charList[a])
			{
			case "A":
				returnWord+="E";
				break;
			case "E":
				returnWord+="A";
				break;
			case "O":
				returnWord+="I";
				break;
			case "I":
				returnWord+="O";
				break;
			case "a":
				returnWord+="e";
				break;
			case "e":
				returnWord+="a";
				break;
			case "o":
				returnWord+="i";
				break;
			case "i":
				returnWord+="o";
				break;
			default:
				returnWord+=charList[a];
				break;
			}
		}
		return returnWord;
	}
}


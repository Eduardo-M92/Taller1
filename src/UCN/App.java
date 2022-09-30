package UCN;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class App
{
	public static void main(String[]args) throws IOException
	{
		
		//vectors that save the data of the aliens
		String[] nameSpecies = new String[1000];
		String[] nameAliens = new String[1000];
		String[] homePlanet = new String[1000];
		String[] type = new String[1000];
		String[] universalIdentify = new String[1000];
		double[] alienAge = new double[1000];
		double[] alienHeight = new double[1000];
		double[] alienWeight = new double[1000];
		
		//vectors that save the data of humans
		String[] nationality = new String[1000];
		String[] name = new String[1000];
		String[] region = new String[1000];
		String[] city = new String[1000];
		String[] planet = new String[1000];
		String[] identify = new String[1000];
		double[] age = new double[1000];
		double[] height = new double[1000];
		double[] weight = new double[1000];
		
		String[] uniqueP = new String[500];
		int[] workers = new int[500];
		/*
		 * counter
		 * position 0 alien amount
		 * position 1 human amount
		 */
		int[] counter = new int[2];
		
		counter[0] = readX(nameSpecies, nameAliens, homePlanet, type, universalIdentify, alienAge, alienHeight, alienWeight, uniqueP);
		counter[1] = readH(nationality, name, region, city, planet, identify, age, height, weight, uniqueP, workers);
		
		menu(nameSpecies, nameAliens, homePlanet, type, universalIdentify, alienAge, alienHeight, alienWeight, counter, nationality, name, region, city, planet, identify, age, height, weight, uniqueP, workers);
		
	}
	/**
	 * readX
	* read the file, load the vectors of the aliens
	* @return number of aliens
	*/
	
	public static int readX(String[] nameSpecies, String[] nameAliens, String[] homePlanet, String[] type, String[] universalIdentify, double[] alienAge, double[] alienHeight, double[] alienWeight, String[] uniqueP) throws FileNotFoundException
	{
		Scanner arch = new Scanner(new File("x.txt"));
		int count = 0;
		
		while(arch.hasNext())
		{
			String[] parts = arch.nextLine().split(",");
			nameSpecies[count] = translator(parts[0]);
			nameAliens[count] = translator(parts[1]);
			universalIdentify[count] = parts[2];
			homePlanet[count] = translator(parts[3]);
			alienAge[count] = Double.valueOf(parts[4])/8;
			alienHeight[count] = Double.valueOf(parts[5])*0.01;
			alienWeight[count] = Double.valueOf(parts[6])*0.001;
			type[count] = parts[7];
			uniquePlanet(uniqueP, translator(parts[3]));
			
			count++;
			
		}
		return count;
	}
	
	/**
	 * readH
	* read file, load human vectors
	* @return number of registered humans
	*/
	public static int readH(String[] nationality, String[] name, String[] region, String[] city, String[] planet, String[] identify, double[] age, double[] height, double[] weight, String[] uniqueP, int[] workers) throws FileNotFoundException
	{
		Scanner arch = new Scanner(new File("h.txt"));
		int count = 0;
		String[] planets;
		
		while(arch.hasNext())
		{
			String[] parts = arch.nextLine().split(",");
			nationality[count] = parts[0].toLowerCase();
			name[count] = parts[1];
			identify[count] = parts[2];
			region[count] = parts[3];
			city[count] = parts[4];
			age[count] = Double.valueOf(parts[5]);
			height[count] = Double.valueOf(parts[6]);
			weight[count] = Double.valueOf(parts[7]);
			planet[count] = parts[8];
			planets = parts[8].split("/");
			for(int a=0;a<planets.length;a++)
			{
				workers[uniquePlanet(uniqueP, planets[a])]+=1;;
			}
			count++;
		}
		return count;
	}
	
	/**
	 * menu
	* displays a general menu
	*/
	public static void menu(String[] nameSpecies, String[] nameAliens, String[] homePlanet, String[] type, String[] universalIdentify, double[] alienAge, double[] alienHeight, double[] alienWeight, int[] counter, String[] nationality, String[] name, String[] region, String[] city, String[] planet, String[] identify, double[] age, double[] height, double[] weight, String[] uniqueP, int[] workers) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		while(!exit)
		{
			System.out.println("----------------------------------------------");
			System.out.println("Gestión Universal");
			System.out.println("----------------------------------------------");
			System.out.println("Opciones: ");
			System.out.println("1)Opciones de extraterrestres");
			System.out.println("2)Opciones de humanos");
			System.out.println("3)Mostrar por planeta");
			System.out.println("4)Salir y guardar datos");
			System.out.print("Ingrese opcion: ");
			String option = sc.nextLine();
			
			switch(option)
			{
			case "1":
				alienMenu(nameSpecies, nameAliens, homePlanet, type, universalIdentify, alienAge, alienHeight, alienWeight,uniqueP, counter);
				break;
			case "2":
				humanMenu(nationality, name, region, city, planet, identify, age, height, weight, uniqueP, workers, counter);
				break;
			case "3":
				planetTable(nameSpecies, nameAliens, homePlanet, type, universalIdentify, alienAge, alienHeight, alienWeight,uniqueP, workers, counter);
				break;
			case "4":
				exit(nameSpecies, nameAliens, homePlanet, type, universalIdentify, alienAge, alienHeight, alienWeight, counter, nationality, name, region, city, planet, identify, age, height, weight);
				exit=true;
				break;
			}
			
		}
	}
	/**
	 * Human menu
	 */
	public static void humanMenu(String[] nationality, String[] name, String[] region, String[] city, String[] planet, String[] identify, double[] age, double[] height, double[] weight,String[] uniqueP,int[] workers, int[] counter)
	{
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		while(!exit)
		{
			System.out.println("----------------------------------------------");
			System.out.println("Menu de opciones Humanas");
			System.out.println("----------------------------------------------");
			System.out.println("1)Ingresas humano");
			System.out.println("2)Modificar humano");
			System.out.println("3)Desplegar por nacionalidad");
			System.out.println("4)Eliminar humano");
			System.out.println("5)Mostrar datos por nacionalidad:");
			System.out.println("6)Regresar al menu princial");
			System.out.print("Ingrese opcion: ");
			String option = sc.nextLine();
			switch(option)
			{
			case "1":
				if(addHuman(nationality, name, region, city, planet, identify, age, height, weight, uniqueP, workers, counter, sc))
				{
					System.out.println("----------------------------------------------");
					System.out.println("Humano añadido al sistema");
					System.out.println("----------------------------------------------");
				}
				else
				{
					System.out.println("----------------------------------------------");
					System.out.println("Humano NO añadido al sistema");
					System.out.println("----------------------------------------------");
				}
				break;
			case "2":
				modifyHuman(nationality, name, region, city, planet, identify, age, height, weight, workers, counter, sc);
				break;
			case "3":
				System.out.println("Ingrese una nacionalidad: ");
				String op1 = sc.nextLine().toLowerCase();
				System.out.println(op1+"s: ");
				for(int a=0;a<counter[1];a++)
				{
					if(nationality[a].toLowerCase().equals(op1))
					{
						System.out.println("----------------------------------------------");
						System.out.println("Nombre: "+name[a]);
						System.out.println("Identificacion: "+identify[a]);
						System.out.println("----------------------------------------------");
					}
				}
				break;
			case "4":
				deleteHuman(nationality, name, region, city, planet, identify, age, height, weight, workers, counter, sc);
				break;
			case "5":
				System.out.print("Ingrese una nacionalidad: ");
				String op2 = sc.nextLine().toLowerCase();
				int cont=0;
				System.out.println(op2+"s: ");
				for(int a=0;a<counter[1];a++)
				{
					if(nationality[a].toLowerCase().equals(op2))
					{
						System.out.println("----------------------------------------------");
						System.out.println("Nombre: "+name[a]);
						System.out.println("Region: "+region[a]);
						System.out.println("Ciudad: "+city[a]);
						System.out.println("Identificacion: "+identify[a]);
						System.out.println("Edad: "+age[a]+"(años)");
						System.out.println("Altura: "+height[a]+"(mt)");
						System.out.println("Peso: "+weight[a]+"(Kg)");
						System.out.println("Planetas trabajados: "+planet[a]);
						System.out.println("----------------------------------------------");
						cont++;
					}
				}
				double porcent = (Double.valueOf(cont)/Double.valueOf(counter[1]))*100;
				System.out.println("("+cont+")"+op2+" de ("+counter[1]+")Humanos");
				System.out.println(porcent+"% de "+op2+"s con respecto a los Humanos totales");
				break;
			case "6":
				exit=true;
				break;
			}
		}
	}
	/**
	 * alien menu
	 */
	public static void alienMenu(String[] nameSpecies, String[] nameAliens, String[] homePlanet, String[] type, String[] universalIdentify, double[] alienAge, double[] alienHeight, double[] alienWeight,String[] uniqueP, int[] counter)
	{
		Scanner sc = new Scanner(System.in);
		boolean exit = false;
		while(!exit)
		{
			System.out.println("----------------------------------------------");
			System.out.println("Menu de opciones Extraterrestres");
			System.out.println("----------------------------------------------");
			System.out.println("1)Ingresar extraterrestre");
			System.out.println("2)Modificar un extraterrestre");
			System.out.println("3)Eliminar extraterrestre");
			System.out.println("4)Buscar por identificación universal");
			System.out.println("5)Desplegar tabla de datos extraterrestres");
			System.out.println("6)Regresar al menu princial");
			System.out.print("Ingrese opcion: ");
			String option = sc.nextLine();
			switch(option)
			{
			case "1":
				if(addAlien(nameSpecies, nameAliens, homePlanet, type, universalIdentify, alienAge, alienHeight, alienWeight,uniqueP, counter,sc))
				{
					System.out.println("----------------------------------------------");
					System.out.println("Extraterrestre añadido al sistema");
					System.out.println("----------------------------------------------");
				}
				else
				{
					System.out.println("----------------------------------------------");
					System.out.println("Extraterrestre NO añadido al sistema");
					System.out.println("----------------------------------------------");
				}
				break;
			case "2":
				modifyAlien(nameSpecies, nameAliens, homePlanet, type, universalIdentify, alienAge, alienHeight, alienWeight, counter, sc);
				break;
			case "3":
				deleteAlien(nameSpecies, nameAliens, homePlanet, type, universalIdentify, alienAge, alienHeight, alienWeight, counter);
				break;
			case "4":
				int pos = searchToUI(universalIdentify, counter);
				if(pos==-1)
				{
					System.out.println("----------------------------------------------");
					System.out.println("Extraterrestre no encontrado");
					System.out.println("----------------------------------------------");
				}
				else
				{
					System.out.println("Extraterrestre: "+nameAliens[pos]);
					System.out.println("Especie: "+nameSpecies[pos]);
					System.out.println("Identificacion Universal: "+ universalIdentify[pos]);
					System.out.println("Planeta de origen: "+homePlanet[pos]);
					System.out.println("Edad: "+alienAge[pos]+" Altura: "+alienHeight[pos]+" Peso: "+alienWeight[pos]);
					System.out.println("Ingrese enter para continuar...");
					option = sc.nextLine();
				}
				break;
			case "5":
				dataTable(type, counter);
				break;
			case "6":
				exit=true;
				break;
			}
		}
	}
	/**
	* modifyHuman
	* allows to modify the data of a human
	*/
	public static void modifyHuman(String[] nationality, String[] name, String[] region, String[] city, String[] planet, String[] identify, double[] age, double[] height, double[] weight, int[] workers, int[] counter, Scanner sc)
	{
		int pos = searchToI(identify, counter);
		if(pos!=-1)
		{
			boolean cycle = true;
			while(cycle)
			{
				System.out.println("----------------------------------------------");
				System.out.println("Modificar Informacion de un humano");
				System.out.println("Esta modificando a: "+name[pos]+", de nacionalidad: "+nationality[pos]+", de la region: "+region[pos]+", de la ciudad: "+city[pos]);
				System.out.println("Su peso es: "+weight[pos]+"(Kg), su altura es: "+height[pos]+"(mt), su edad es: "+age[pos]+"(años)");
				System.out.println("Ha trabajado en el/los planeta/s: "+planet[pos]);
				System.out.println("----------------------------------------------");
				System.out.println("1)Nacionalidad");
				System.out.println("2)Nombre");
				System.out.println("3)Region");
				System.out.println("4)Ciudad");
				System.out.println("5)planetas");
				System.out.println("6)edad");
				System.out.println("7)Altura");
				System.out.println("8)Peso");
				System.out.println("9)Regresar y guardar");
				System.out.print("Ingrese opcion: ");
				String option = sc.nextLine();
				
				switch(option)
				{
				case("1"):
					System.out.print("Nuevo nacionalidad: ");
					nationality[pos] = sc.nextLine();
					break;
				case("2"):
					System.out.print("Nuevo nombre: ");
					name[pos] = sc.nextLine();
					break;
				case("3"):
					System.out.print("Nuevo region: ");
					region[pos] = sc.nextLine();
					break;
				case("4"):
					System.out.print("Nuevo ciudad: ");
					city[pos] = sc.nextLine();
					break;
				case("5"):
					planetOptions(planet, pos, sc);
					break;
				case("6"):
					System.out.print("Nueva edad: ");
					age[pos] = Integer.valueOf(sc.nextLine());
					break;
				case("7"):
					System.out.print("Nuevo altura: ");
					height[pos] = Double.valueOf(sc.nextLine());
					break;
				case("8"):
					System.out.print("Nuevo peso: ");
					weight[pos] = Double.valueOf(sc.nextLine());
					break;
				case("9"):
					cycle=false;
					break;	
				}
			}
		}
	}
	
	/**
	* modify Alien
	* allows to modify the data of a human
	*/
	public static void modifyAlien(String[] nameSpecies, String[] nameAliens, String[] homePlanet, String[] type, String[] universalIdentify, double[] alienAge, double[] alienHeight, double[] alienWeight, int[] counter, Scanner sc)
	{
		int alinePos = searchToUI(universalIdentify, counter);
		if(alinePos!=-1)
		{
			boolean cycle = true;
			while(cycle)
			{
				System.out.println("----------------------------------------------");
				System.out.println("Modificar Informacion de un extraterrestre");
				System.out.println("Esta modificando a: "+nameAliens[alinePos]+", del planeta: "+homePlanet[alinePos]+", de la especie: "+nameSpecies[alinePos]+", estructura: "+type[alinePos]);
				System.out.println("Su peso es: "+alienWeight[alinePos]+"(Kg), su altura es: "+alienHeight[alinePos]+"(mt), su edad es: "+alienAge[alinePos]+"(Años Humanos)");
				System.out.println("----------------------------------------------");
				System.out.println("1)Nombre especie");
				System.out.println("2)Nombre");
				System.out.println("3)Planeta de origen");
				System.out.println("4)Tipo de estructura");
				System.out.println("5)Edad");
				System.out.println("6)Altura");
				System.out.println("7)Peso");
				System.out.println("8)Regresar y guardar");
				System.out.print("Ingrese opcion: ");
				String option = sc.nextLine();
				
				switch(option)
				{
				case("1"):
					System.out.print("Nuevo nombre de especie: ");
					nameSpecies[alinePos] = sc.nextLine();
					break;
				case("2"):
					System.out.print("Nuevo nombre: ");
					nameAliens[alinePos] = sc.nextLine();
					break;
				case("3"):
					System.out.print("Nuevo planeta de origen: ");
					homePlanet[alinePos] = sc.nextLine();
					break;
				case("4"):
					System.out.print("Nuevo tipo(V)(I)(F): ");
				String typeA = sc.nextLine().toUpperCase();
				while(!typeA.equals("V")&&!typeA.equals("I")&&!typeA.equals("F"))
				{
					System.out.println("Tipo incorrecto (V)(I)(F)");
					System.out.print("Tipo(Vertebrado(V), Invertebrado(I), Flexible(F)): ");
					typeA = sc.nextLine().toUpperCase();
				}
					type[alinePos] = typeA;
					break;
				case("5"):
					System.out.print("Nuevo edad(Años extraterrester): ");
					alienAge[alinePos] = Integer.valueOf(sc.nextLine());
					break;
				case("6"):
					System.out.print("Nuevo altura(Centimetros): ");
					alienHeight[alinePos] = Double.valueOf(sc.nextLine());
					break;
				case("7"):
					System.out.print("Nuevo peso(Gramos): ");
					alienWeight[alinePos] = Double.valueOf(sc.nextLine());
					break;
				case("8"):
					cycle=false;
					break;	
				}
			}
		}
	}
	/**
	 * addHuman
	* add a human to the system
	*/
	private static boolean addHuman(String[] nationality, String[] name, String[] region, String[] city, String[] planet, String[] identify, double[] age, double[] height, double[] weight, String[] uniqueP, int[] workers, int[] counter, Scanner sc)
	{
		int humanCounter = counter[1];
		String planets = "";
		System.out.print("Nacionalidad: ");
		String hNationality = sc.nextLine();
		System.out.print("Nombre: ");
		String hName = sc.nextLine();
		System.out.print("Region: ");
		String hRegion = sc.nextLine();
		System.out.print("Ciudad: ");
		String hCity = sc.nextLine();
		System.out.print("Cantidad de planetas en los que ha trabajado: ");
		int cantP=Integer.valueOf(sc.nextLine());
		for(int a=0;a<cantP;a++)
		{
			System.out.print("Planeta ("+(a+1)+"): ");
			String hPlanet = sc.nextLine();
			if(a==0)
			{
				planets+=hPlanet;
			}else {planets+="/"+hPlanet;}
			workers[uniquePlanet(uniqueP, hPlanet)]+=1;
		}
		System.out.print("Ingrese la identificacion de 7 diguitos numericos: ");
		String I = sc.nextLine();
		while(I.split("").length!=7)
		{
			System.out.println("----------------------------------------------");
			System.out.println("La identificacion tiene que tener 7 digitos");
			System.out.println("----------------------------------------------");
			System.out.print("Ingrese la identificacion de 7 diguitos numericos: ");
			I = sc.nextLine();
		}
		if(searchToI(identify, counter, I)!=-1)
		{
			System.out.println("----------------------------------------------");
			System.out.println("(ID duplicada)");
			System.out.println("----------------------------------------------");
			return false;
		}
		System.out.print("Edad: ");
		int hAge = Integer.valueOf(sc.nextLine());
		System.out.print("Altura: ");
		double hHeight = Double.valueOf(sc.nextLine());
		System.out.print("Peso: ");
		double hWeight = Double.valueOf(sc.nextLine());
		nationality[humanCounter] = hNationality;
		name[humanCounter] = hName;
		region[humanCounter] = hRegion;
		city[humanCounter] = hCity;
		planet[humanCounter] = planets;
		age[humanCounter] = hAge;
		identify[humanCounter] = I;
		height[humanCounter] = hHeight;
		weight[humanCounter] = hWeight;
		counter[1]+=1;
		return true;
	}
	
	/**
	 * addAlien
	* add a alien to the system
	*/
	public static boolean addAlien(String[] nameSpecies, String[] nameAliens, String[] homePlanet, String[] type, String[] universalIdentify, double[] alienAge, double[] alienHeight, double[] alienWeight, String[] uniqueP, int[] counter,Scanner sc)
	{
		int alienCounter = counter[0];
		System.out.print("Nombre de la especie: ");
		String eName = sc.nextLine();
		System.out.print("Nombre: ");
		String name = sc.nextLine();
		System.out.print("Ingrese la identificacion de 8 diguitos numericos: ");
		String UI = sc.nextLine();
		while(UI.split("").length!=8)
		{
			System.out.println("----------------------------------------------");
			System.out.println("La identificacion tiene que tener 8 digitos");
			System.out.println("----------------------------------------------");
			System.out.print("Ingrese la identificacion de 8 diguitos numericos: ");
			UI = sc.nextLine();
		}
		if(searchToUI(universalIdentify, counter, UI)!=-1)
		{
			System.out.println("----------------------------------------------");
			System.out.println("(ID duplicada)");
			System.out.println("----------------------------------------------");
			return false;
		}
		System.out.print("Planeta origen: ");
		String planet = sc.nextLine();
		uniquePlanet(uniqueP, planet);
		System.out.print("Edad(Años extraterrestres): ");
		int age = Integer.valueOf(sc.nextLine())/8;
		System.out.print("Altura(Centimetros): ");
		double height = Double.valueOf(sc.nextLine())*0.01;
		System.out.print("Peso(Gramos): ");
		double weight = Double.valueOf(sc.nextLine())*0.001;
		System.out.print("Tipo(Vertebrado(V), Invertebrado(I), Flexible(F)): ");
		String typeA = sc.nextLine().toUpperCase();
		while(!typeA.equals("V")&&!typeA.equals("I")&&!typeA.equals("F"))
		{
			System.out.println("Tipo incorrecto (V)(I)(F)");
			System.out.print("Tipo(Vertebrado(V), Invertebrado(I), Flexible(F)): ");
			typeA = sc.nextLine().toUpperCase();
		}
		nameSpecies[alienCounter] = translator(eName);
		nameAliens[alienCounter] = translator(name);
		homePlanet[alienCounter] = translator(planet);
		type[alienCounter] = typeA;
		universalIdentify[alienCounter] = UI;
		alienAge[alienCounter] = age;;
		alienHeight[alienCounter] = height;
		alienWeight[alienCounter] = weight;
		counter[0]+=1;
		return true;
	}
	/**
	 * deleteHuman
	 * delete a human of the system
	 */
	public static boolean deleteHuman(String[] nationality, String[] name, String[] region, String[] city, String[] planet, String[] identify, double[] age, double[] height, double[] weight, int[] workers, int[] counter, Scanner sc)
	{
		int pos = searchToI(identify, counter);
		String temporalPlanets;
		String[] planets;
		if(pos!=-1)
		{
			int a=0;
			temporalPlanets = planet[pos];
			for(a=pos;a<counter[1]-1;a++)
			{
				nationality[a]=nationality[a+1];
				name[a]=name[a+1];
				region[a]=region[a+1];
				city[a]=city[a+1];
				identify[a]=identify[a+1];
				age[a]=age[a+1];
				height[a]=height[a+1];
				weight[a]=weight[a+1];
				
				planet[a]=planet[a+1];
			}
			planets = temporalPlanets.split("/");
			for(int b=0;b<planets.length;b++)
			{
				workers[uniquePlanet(planets, planets[b])]-=1;
			}
			nationality[a]=null;
			name[a]=null;
			region[a]=null;
			city[a]=null;
			planet[a]=null;
			identify[a]=null;
			age[a]=0;
			height[a]=0;
			weight[a]=0;
			counter[1]-=1;
			return true;
		}
		else {System.out.println("El Humano no existe en el sistema");return false;}
	}
	/**
	 * deleteAlien
	 * delete a alien of the system
	 */
	public static boolean deleteAlien(String[] nameSpecies, String[] nameAliens, String[] homePlanet, String[] type, String[] universalIdentify, double[] alienAge, double[] alienHeight, double[] alienWeight, int[] counter)
	{
		int alienPos = searchToUI(universalIdentify, counter);
		if(alienPos!=-1)
		{
			int a=0;
			for(a=alienPos;a<counter[0]-1;a++)
			{
				nameSpecies[a]=nameSpecies[a+1];
				nameAliens[a]=nameAliens[a+1];
				homePlanet[a]=homePlanet[a+1];
				type[a]=type[a+1];
				universalIdentify[a]=universalIdentify[a+1];
				alienAge[a]=alienAge[a+1];
				alienHeight[a]=alienHeight[a+1];
				alienWeight[a]=alienWeight[a+1];
			}
			nameSpecies[a]=null;
			nameAliens[a]=null;
			homePlanet[a]=null;
			type[a]=null;
			universalIdentify[a]=null;
			alienAge[a]=0;
			alienHeight[a]=0;
			alienWeight[a]=0;
			counter[0]-=1;
			return true;
		}
		else {System.out.println("El extraterrestre no existe en el sistema");return false;}
	}
	
	/**
	 * planetTable
	* displays a table of planets to choose from
	* shows the aliens living on the planet and the percentage of aliens living on the planet
	*/
	public static void planetTable(String[] nameSpecies, String[] nameAliens, String[] homePlanet, String[] type, String[] universalIdentify, double[] alienAge, double[] alienHeight, double[] alienWeight, String[] uniqueP, int[] workers, int[] counter)
	{
		int cont=0;
		int a=0;
		Scanner sc = new Scanner(System.in);
		System.out.print("Planetas: ");
		while(uniqueP[a]!=null)
		{
			System.out.print("- "+uniqueP[a]+" ");
			a++;
		}
		System.out.println("");
		System.out.print("Ingrese el nombre del planeta: ");
		String option = sc.nextLine();
		for(int i=0;i<counter[0];i++)
		{
			if(homePlanet[i].toLowerCase().equals(option.toLowerCase()))
			{
				System.out.println("----------------------------------------------");
				System.out.println("Identificacion univesal: "+universalIdentify[i]);
				System.out.println("Extraterrestre: "+nameAliens[i]+", del planeta: "+homePlanet[i]+", de la especie: "+nameSpecies[i]+", estructura: "+type[i]);
				System.out.println("Su peso es: "+alienWeight[i]+"(Kg), su altura es: "+alienHeight[i]+"(mt), su edad es: "+alienAge[i]+"(Años Humanos)");
				System.out.println("----------------------------------------------");
				cont++;
			}
		}
		double porcent = (Double.valueOf(cont)/Double.valueOf(counter[0]))*100;
		System.out.println(porcent+"% de extraterrestre viviendo en "+option+" del total("+counter[0]+") extraterrestres");
		System.out.println("Humanos trabajando en "+option+": "+workers[uniquePlanet(uniqueP, option)]);
	}
	
	/**
	 * dataTable
	 * shows the percentages of the types of aliens with respect to the total
	 */
	public static void dataTable(String[] type, int[] counter)
	{
		int cantV=0;
		int cantI=0;
		int cantF=0;
		int cant = counter[0];
		
		for(int a=0;a<cant;a++)
		{
			switch(type[a])
			{
			case("V"):
				cantV++;
				break;
			case("I"):
				cantI++;
				break;
			case("F"):
				cantF++;
				break;
			}
		}
		System.out.println("Total de extraterrestres: "+ cant);
		System.out.println("Vertebrados totales: "+cantV+" Porcentaje respecto al total: "+(Double.valueOf(cantV)/Double.valueOf(cant))*100+"%");
		System.out.println("Invertebrados totales: "+cantI+" Porcentaje respecto al total: "+(Double.valueOf(cantI)/Double.valueOf(cant))*100+"%");
		System.out.println("Flexibles totales: "+cantF+" Porcentaje respecto al total: "+(Double.valueOf(cantF)/Double.valueOf(cant))*100+"%");
	}
	
	/**
	 * 
	 * @param identify
	 * @param counter
	 * search with respect to the id to the human in the system
	 * @return position of the human in the system
	 */
	public static int searchToI(String[] identify, int[] counter)
	{
		Scanner sc = new Scanner(System.in);
		String I;
		int a = 0;
		System.out.print("Ingrese la identificacion de 7 diguitos numericos: ");
		I = sc.nextLine();
		while(I.split("").length!=7)
		{
			System.out.println("----------------------------------------------");
			System.out.println("La identificacion tiene que tener 7 digitos");
			System.out.println("----------------------------------------------");
			System.out.print("Ingrese la identificacion de 7 diguitos numericos: ");
			I = sc.nextLine();
		}	
		for (a=0;a<counter[1];a++)
		{
			if(identify[a].equals(I))
			{
				break;
			}
		}
		if(a==counter[1])
		{
			return -1;
		}else { return a;}
	}
	
	/**
	 * searchToI
	 * search with respect to the id to the human in the system
	 * @return position of the human in the system
	 */
	public static int searchToI(String[] identify, int[] counter, String I)
	{
		int a = 0;
		for (a=0;a<counter[1];a++)
		{
			if(identify[a].equals(I))
			{
				break;
			}
		}
		if(a==counter[1])
		{
			return -1;
		}else { return a;}
	}
	
	/**
	 * searchToUI
	 * search with respect to the universal id to the alien in the system
	 * @return position of the alien in the system
	 */
	public static int searchToUI(String[] universalIdentify, int[] counter)
	{
		Scanner sc = new Scanner(System.in);
		String UI;
		int a = 0;
		System.out.print("Ingrese la identificacion de 8 diguitos numericos: ");
		UI = sc.nextLine();
		while(UI.split("").length!=8)
		{
			System.out.println("----------------------------------------------");
			System.out.println("La identificacion tiene que tener 8 digitos");
			System.out.println("----------------------------------------------");
			System.out.print("Ingrese la identificacion de 8 diguitos numericos: ");
			UI = sc.nextLine();
		}	
		for (a=0;a<counter[0];a++)
		{
			if(universalIdentify[a].equals(UI))
			{
				break;
			}
		}
		if(a==counter[0])
		{
			return -1;
		}else { return a;}
	}
	
	/**
	 * searchToUI
	 * search with respect to the universal id to the alien in the system
	 * @return position of the alien in the system
	 */
	public static int searchToUI(String[] universalIdentify, int[] counter, String UI)
	{
		int a = 0;
		for (a=0;a<counter[0];a++)
		{
			if(universalIdentify[a].equals(UI))
			{
				break;
			}
		}
		if(a==counter[0])
		{
			return -1;
		}else { return a;}
	}
	
	/**
	 * uniquePlanet
	 * check if the planet exists within the unique planets list
	 * if it exists it returns the position in the list 
	 * if it doesn't exist it adds it and returns the position in which it was added
	 */
	private static int uniquePlanet(String[] uniqueP, String nameP)
	{
		int a = 0;
		while(uniqueP[a]!=null)
		{
			if(uniqueP[a].toLowerCase().equals(nameP.toLowerCase()))
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
	
	/**
	 * planetOptions
	 * modifies planets a human has worked on
	 */
	public static void planetOptions(String[] planet, int pos, Scanner sc)
	{
		boolean cycle = true;
		while(cycle)
		{
			String stringPlanets = planet[pos];
			String[] planets = stringPlanets.split("/");
			String name="";
			System.out.println("Planetas: "+planet[pos]);
			System.out.println("1)Añadir planeta");
			System.out.println("2)Eliminar planeta");
			System.out.println("3)Regresar");
			System.out.print("Opcion: ");
			String option = sc.nextLine();
			switch (option)
			{
			case "1":
				System.out.print("nombre del planeta: ");
				name = sc.nextLine();
				if((planets.length-1)==0)
				{
					stringPlanets += name;
					System.out.println("Planeta: "+name+" Añadido");
				}
				else
				{
					int a;
					for(a=0;a<planets.length;a++)
					{
						if(planets[a].equals(name))
						{
							break;
						}
					}
					if(a==(planets.length-1))
					{
						System.out.println("Planeta ya listado");
					}
					else 
					{
						stringPlanets+="/"+name;
						System.out.println("Planeta: "+name+" Añadido");
					}
				}
				planet[pos] = stringPlanets;
				break;
			case "2":
				stringPlanets ="";
				int a;
				System.out.print("nombre del planeta: ");
				name = sc.nextLine();
				for(a=0;a<planets.length;a++)
				{
					if(planets[a].equals(name))
					{
						int i;
						for(i=a;i<planets.length-1;i++)
						{
							planets[i] = planets[i+1];
						}
						planets[i]=null;
						for(int b=0;b<planets.length-1;b++)
						{
							if(b==0)
							{
								stringPlanets += planets[b];
							}else {stringPlanets += "/"+planets[b];}
						}
						System.out.println("Planeta: "+name+" Eliminado");
						planet[pos] = stringPlanets;
						break;
					}
				}
				if(a==(planets.length))
				{
					System.out.println("Planeta NO encontrado");
				}
				break;
			case "3":
				cycle=false;
				break;
			}
		}
	}
	
	/**
	 * translator
	 * a two-way translator of the alien language
	 * @return the translated word
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
	
	/**
	 * collects system information and saves it in 2 .txt files
	 */
	public static void exit(String[] nameSpecies, String[] nameAliens, String[] homePlanet, String[] type, String[] universalIdentify, double[] alienAge, double[] alienHeight, double[] alienWeight, int[] counter, String[] nationality, String[] name, String[] region, String[] city, String[] planet, String[] identify, double[] age, double[] height, double[] weight) throws IOException
	{
		FileWriter h = new FileWriter("h.txt");
		PrintWriter pw1 = new PrintWriter(h);
		for(int a=0;a<counter[1];a++)
		{
			pw1.println(nationality[a]+","+name[a]+","+identify[a]+","+region[a]+","+city[a]+","+(int) age[a]+","+(int) height[a]+","+(int) weight[a]+","+planet[a]);
		}
		FileWriter x = new FileWriter("x.txt");
		PrintWriter pw2 = new PrintWriter(x);
		for(int a=0;a<counter[1];a++)
		{
			pw2.println(translator(nameSpecies[a])+","+translator(nameAliens[a])+","+universalIdentify[a]+","+translator(homePlanet[a])+","+(int) (alienAge[a]*8)+","+(int) (alienHeight[a]/0.01)+","+(int) (alienWeight[a]/0.001)+","+type[a]);
		}
		pw1.close();
		pw2.close();
	}
}
/*
 0 1 0 3 0 6
 1 2 1 4
 2 5
 3 4
 4 5
 6 7 6 9 6 12
 7 8 7 10
 8 11
 9 10
 10 11
 12 13 12 15 12 18
 13 14 13 16
 14 17
 15 16
 16 17
 18 19 18 21 18 24
 19 20 19 22
 20 23
 21 22
 22 23
 24 25 24 27 
 25 26 25 28
 26 29
 27 28
 28 29
 */

import java.util.* ;

class car{
	int name,place ;
	car(int temp){
		name = temp ;
		place = -2 ;
	}
}

public class Parking {
	
	public static void main(String[]args){
		
		//define parking as a graph 
		Scanner Scan = new Scanner (System.in) ;
		int[] parking_lot = new int[30] ;
		Arrays.fill(parking_lot, -1) ;
		
		// read s edges of graph
		int[][] matrix = new int[30][30] ;
		for(int i=0 ; i<39 ; i++){
			int x = Scan.nextInt() ;
			int y = Scan.nextInt() ;
			matrix[x][y] = 1 ;
			matrix[y][x] = 1 ;
		}
		
		//bfs baraye peyda kardane nazdiktarin masir az source b har jaygah ya har node
		
		int[] best = new int[30] ;
		int[] prev = new int[30] ;
		Arrays.fill(prev, -1) ;
		
		boolean[] visited = new boolean[30];
		LinkedList<Integer> queue = new LinkedList<Integer>() ;
				
		int source = 0 ;
				
		visited[source] = true ;
		best[source] = 0 ;
		queue.addFirst(source) ;
			
		while(!queue.isEmpty()){
			int at = queue.removeLast();
			
			for(int i=0 ; i<30 ; i++){
				if(!visited[i] && matrix[at][i]==1){
					best[i] = best[at]+1 ;
					visited[i] = true ;
					prev[i] = at ;
					queue.addFirst(i) ;
				}
			}
		}
		{
			best[0] = -1 ;
			best[6] = -1 ;
			best[12] = -1 ;
			best[18] = -1 ;
			best[24] = -1 ;
			parking_lot[0] = -2 ;
			parking_lot[6] = -2 ;
			parking_lot[12] = -2 ;
			parking_lot[18] = -2 ;
			parking_lot[24] = -2 ;
		}
		//.............&&.............
		for(int i=0;i<30;i++){
			System.out.print(best[i]+" ") ;
		}
		
		//.............
		//LinkedList< car> CARS = new LinkedList<>() ;
		//.........	
		while(true){
			System.out.println("\n1.Enter a car to parking") ;
			System.out.println("2.Exit a car") ;
			System.out.println("3.Exit program") ;
			
			int temp = Scan.nextInt() ;
			if(temp==1){
				
				System.out.println("Enter Ur car name :") ;
				car new_car = new car(Scan.nextInt()) ;
				//find a place for parking............
				
				int parking_place = -1 ;
				for(int i=1 ; i<8 ; i++){
					for(int j=1 ; j<30 ; j++){
						if(best[j]==i && parking_lot[j]==-1){
							System.out.println("man umadam inja ba "+j) ;
							parking_place = j ;
							System.out.println("parking_palce "+parking_place) ;
							break ;
						}
					}
					if(parking_place!=-1){
						break ;
					}
				}
				System.out.println("\njaye park "+parking_place) ;
					
				//...........................
				LinkedList<Integer> masir = new LinkedList<>() ;
				int temp1 = parking_place ;
				
				while(temp1!=0){
					masir.add(temp1) ;
					temp1 = prev[temp1] ;
					System.out.println("temp"+temp1) ;
				}
				masir.add(0) ;
				System.out.println(masir) ;
				
				int temp2, at =  masir.removeFirst() ;
				System.out.print(masir) ;
				parking_lot[0] = new_car.name ;
				
				while(masir.size()!=0){
					temp2 = masir.removeFirst() ;
					if(parking_lot[temp2]==-2){
						System.out.println("move "+parking_lot[0]+" from 0 to "+temp2) ;
						System.out.println("move "+parking_lot[0]+" from "+temp2+" to "+at) ;
						parking_lot[at] = parking_lot[0] ;
						new_car.place = at ;
						parking_lot[0] = -2 ;
						break ;
					}
					System.out.println("move "+parking_lot[temp2]+" from "+temp2+" to "+at) ;
					parking_lot [at] = parking_lot [temp2] ;
					if(temp2==0){
						parking_lot[temp2] = -2 ;
						new_car.place = at ;
						//CARS.add(new_car) ;
					}
					at = temp2 ;
				}
				for(int i=0;i<30;i++)
					System.out.print(i+":"+parking_lot[i]+"  ") ;
				
				//for(int j=0 ; j<CARS.size() ; j++)
					//System.out.print(CARS.get(j).name+":"+CARS.get(j).place+" ") ;
			}
			if(temp==2){
				
				System.out.println("which car !?") ;
				int wanted_car = Scan.nextInt() ;
				// find mogheEate fe'lie car
				int wanted_car_place = -1 ;
				for(int i=0 ; i<30 ; i++){
					if(parking_lot[i] == wanted_car){
						wanted_car_place = i ;
					}
				}
				int Wanted_Car_Place = wanted_car_place ;
				
				//bfs ta asansore tabaghe
				boolean exit = false ;
				while(!exit){
					int source2 = wanted_car_place ;
					int sink = 6*(wanted_car_place/6) ;
					
					//bfs vase rahaye residan b asasonsore tabaghe
					int[] best2 = new int[30] ;
					int[] prev2 = new int[30] ;
					Arrays.fill(prev, -1) ;
					
					boolean[] visited2 = new boolean[6];
					LinkedList<Integer> queue2 = new LinkedList<Integer>() ;
							
					visited2[source2%6] = true ;
					best2[source2%6] = 0 ;
					queue2.addFirst(source2) ;
						
					while(!queue2.isEmpty()){
						int at = queue2.removeLast();
						
						for(int i=0 ; i<6 ; i++){
							if(!visited2[i] && matrix[at][i+6*(wanted_car_place/6)]==1){
								best2[i] = best[at]+1 ;
								visited2[i] = true ;
								prev2[i] = at ;
								queue2.addFirst(i) ;
							}
						}
					}
					
					//......................
					LinkedList<Integer> masir_khoruj = new LinkedList<>() ;
					int komaki = 6*(wanted_car_place/6) ;
					
					while(komaki!=wanted_car_place){
						masir_khoruj.add(komaki) ;
						System.out.print("komaki:"+komaki+" komaki%^:"+komaki%6) ;
						komaki = prev2[komaki%6] ;
						//System.out.println("temp"+temp1) ;
					}
					masir_khoruj.add(wanted_car_place) ;
					System.out.println(masir_khoruj) ;
					
					int help, at =  masir_khoruj.removeFirst() ;
					System.out.print(masir_khoruj) ;
					
					while(masir_khoruj.size()!=0){
						help = masir_khoruj.removeFirst() ;
						System.out.println("move "+parking_lot[help]+" from "+help+" to "+at) ;
						parking_lot [at] = parking_lot [help] ;
						if(parking_lot[help]==wanted_car){
							parking_lot[help] = -1 ;
							wanted_car_place = at ;
						}
						at = help ;
					}
					for(int i=0;i<30;i++)
						System.out.print(i+":"+parking_lot[i]+"  ") ;
						//hala bayad vase unke tu 0 hast y ja peyda konim
					
					if(wanted_car_place==6*(/6))
					
					//find a place for that !!
					
				}
				
			}
			if(temp==3){
				break ;
			}
		}
	}
}

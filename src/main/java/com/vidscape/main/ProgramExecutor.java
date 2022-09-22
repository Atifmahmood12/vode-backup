package com.vidscape.main;

import java.io.IOException;

public class ProgramExecutor{

	public static void main(String[] args) throws IOException {
		
		/*Iterator<Map<String, String>> genreIterator = MapGenerator.getGenreIterator();
		
		while (genreIterator.hasNext()) {
			Map<String, String> tempMap = genreIterator.next();
			System.out.println(tempMap.get("TC-ID");
			
		}*/
				/*System.out.println(finalMap.size());
				
				Map<Integer ,List> BannerMap = finalMap.get("Banner");
 				
				System.out.println(BannerMap.size());
				
				System.out.println(finalMap.get("Banner").get("TC-Description"));
				 for(int i=0; i<BannerMap.size(); i++) {
					 
					 System.out.println(BannerMap.get("LanguageSelection"));
				 }
		*/
		try {
			System.out.println("in main classs");
			XMLSuites.xmlSuiteExecutor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}
	
	
	

}

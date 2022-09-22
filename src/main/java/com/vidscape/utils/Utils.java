package com.vidscape.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vidscape.pojo.common.Translation;
import com.vidscape.pojo.moviecontent.Contributors;
import com.vidscape.pojo.subscription.SubscriptionRoot;

public class Utils {

	public static void main(String args[]) throws Exception {
		
		
		String contrinutor = "Director|Producer|Actor";
		
		String sortNameEng = "atif|faiza|aqsa";
		String sortNameVi = "atifvi|faizavi|aqsavi";
		
		String firstNameEng = "F_atif|F_faiza|F_aqsa";
		String firstNameVi = "F_atifvi|F_faizavi|F_aqsavi";
		String lastNameEng = "L_atif|L_faiza|L_aqsa";
		String LasNameVi = "L_atifvi|L_faizavi|L_aqsavi";
		String fullNameEng = "FU_atif|FU_faiza|FU_aqsa";
		String fullNameVi = "FU_atifvi|FU_faizavi|FU_aqsavi";
		
		System.out.println(putContributorValuesInJSon(contrinutor,sortNameEng,firstNameEng,lastNameEng,fullNameEng,sortNameVi,firstNameVi,LasNameVi,fullNameVi));
		ObjectMapper mapper1 = new ObjectMapper();
		System.out.println("=============================================");
		System.out.println(mapper1.writerWithDefaultPrettyPrinter().writeValueAsString(putContributorValuesInJSon(contrinutor,sortNameEng,firstNameEng,lastNameEng,fullNameEng,sortNameVi,firstNameVi,LasNameVi,fullNameVi)).toString());
		

	}

	public static String[] splitStringToArray(String inputData) {
		try {
			String[] array = inputData.split("\\|", -1);
			return array;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Contributors> putContributorValuesInJSon(String contibutor, String sortNameEng,
			String firstNameEng, String lastNameEng, String fullNameEng, String sortNameVi, String firstNameVi,
			String lastNameVi, String fullNameVi) {

		try {
			
			List<Contributors> cont = new ArrayList<Contributors>();
			String[] contributorArray = splitStringToArray(contibutor);
			String[] sortNameArrayEng = splitStringToArray(sortNameEng);
			String[] firstNameArrayEng = splitStringToArray(firstNameEng);
			String[] lastNameArrayEng = splitStringToArray(lastNameEng);
			String[] fullNameArrayEng = splitStringToArray(fullNameEng);

			String[] sortNameArrayVi = splitStringToArray(sortNameVi);
			String[] firstNameArrayVi = splitStringToArray(firstNameVi);
			String[] lastNameArrayVi = splitStringToArray(lastNameVi);
			String[] fullNameArrayVi = splitStringToArray(fullNameVi);

			// For vi Content
			for (int i = 0; i < contributorArray.length; i++) {

				Contributors contributors = new Contributors();
				Translation sortableName = new Translation();
				Translation firstName = new Translation();
				Translation lastName = new Translation();
				Translation fullName = new Translation();
				
				contributors.setContribution(contributorArray[i]);

				sortableName.setEng(sortNameArrayEng[i]);
				sortableName.setVi(null);
				contributors.setSortableName(sortableName);

				firstName.setEng(firstNameArrayEng[i]);
				firstName.setVi(null);
				contributors.setFirstName(firstName);

				lastName.setEng(lastNameArrayEng[i]);
				lastName.setVi(null);
				contributors.setLastName(lastName);

				fullName.setEng(fullNameArrayEng[i]);
				fullName.setVi(null);
				contributors.setFullName(fullName);
				
				cont.add(contributors);
			}

			// For Eng Content
			for (int i = 0; i < contributorArray.length; i++) {
				Contributors contributors = new Contributors();
				Translation sortableName = new Translation();
				Translation firstName = new Translation();
				Translation lastName = new Translation();
				Translation fullName = new Translation();

				contributors.setContribution(contributorArray[i]);

				sortableName.setEng(null);
				sortableName.setVi(sortNameArrayVi[i]);
				contributors.setSortableName(sortableName);

				firstName.setEng(null);
				firstName.setVi(firstNameArrayVi[i]);
				contributors.setFirstName(firstName);

				lastName.setEng(null);
				lastName.setVi(lastNameArrayVi[i]);
				contributors.setLastName(lastName);

				fullName.setEng(null);
				fullName.setVi(fullNameArrayVi[i]);
				contributors.setFullName(fullName);
				
				cont.add(contributors);
			}
			return cont;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	public Map<String, String> ExtractDataFromIterator(Iterator<Map<String, String>> DataIterator, String TCID) {
		try {
			Map<String, String> tempMap = null;
			while (DataIterator.hasNext()) {
				tempMap = DataIterator.next();
				if (tempMap.get("TC-ID").equals(TCID)) {
					return tempMap;
				}
			}
		} catch (Exception e) {

		}
		return null;
	}

}

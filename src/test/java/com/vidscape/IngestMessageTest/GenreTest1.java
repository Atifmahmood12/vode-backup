package com.vidscape.IngestMessageTest;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.vidscape.dataproviders.DataProviders;
import com.vidscape.utils.MessageIngester;

public class GenreTest1 {

	MessageIngester mIngester = new MessageIngester();
	Map<String, String> gMap = null;

	@Test
	public void mainTest() {
		try {
			for (int i = 0; i < 5; i++) {
				BuildDataForGenre();
				IngestDataForGenre();
				VerifyGenreTest();
				System.out.println("EXECUTION CYCLE NO. " + i);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Test
	public void BuildDataForGenre() {
		try {
			int a = 1;
			System.out.println(mIngester.getClass());
			Assert.assertTrue(a == 0);
		} catch (AssertionError e) {
			throw e;
		}
	}

	@Test
	public void IngestDataForGenre() {
		try {
			System.out.println("in IngestDataForGenre");

		} catch (AssertionError e) {
			throw e;
		}

	}

	public void VerifyGenreTest() {
		try {

			System.out.println("in VerifyGenreTest");
		} catch (Exception e) {
			throw e;
		}

	}

}

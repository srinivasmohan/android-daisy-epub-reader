package com.ader.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.ader.DaisyReader;
import com.ader.HomeScreen;
import com.jayway.android.robotium.solo.Solo;

public class ExampleTest extends ActivityInstrumentationTestCase2<HomeScreen> {
	private static final String SETTINGS = "Settings";

	private Solo solo;
	
	private static Class<?> launcherActivityClass;
	
	static {
		try {
			launcherActivityClass = Class.forName("com.ader.HomeScreen");
		} catch (ClassNotFoundException cnfe) {
			throw new RuntimeException(cnfe);
		}
	}

	public ExampleTest() {
		super("com.ader", HomeScreen.class);
	}
	
	@Override
	protected void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	@Override
	protected void tearDown() throws Exception {
		try {
			solo.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		getActivity().finish();
		super.tearDown();
	}

	public void testTrue() {
		assertTrue("This better pass!", true);
	}
	
	public void testCanSelectSettingsButton() {
		// Note: this is a crude hard-coded test, we will clean up soon.
		ArrayList<Button> buttons = solo.getCurrentButtons();
		assertEquals("Expected 5 buttons on the homescreen", 5, buttons.size());
		for (Button button: buttons) {
			if (button.getText().equals(SETTINGS)) {
				solo.clickOnButton(SETTINGS);
				return;
			}
		}
		fail("Could not find button labelled " + SETTINGS);
		
	}

}

package com.acme.a3csci3130;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.widget.ListView;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.anything;

/**
 * Created by Will Stone on 14/03/18. Test each CRUD action.
 * In order, the tests will create a contact, verify that the contact was created (create)
 * , verify that the contact's information is correct (read), update the contact's name and verify
 * that it is correct (update), and erase the contact and verify that it is gone (delete).
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CRUDTests {
    @Rule
    public ActivityTestRule<MainActivity> CRUDTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private String createName = "SomeName";
    private String updateName = "SomeOtherName";
    private String createBusinessNumber = "123456789";
    private String createPrimaryBusiness = "Fish Monger";
    private String createAddress = "AndroidIsAPain";
    private String createProvince = "NS";

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void aCreateTest(){
        Espresso.onView(withId(R.id.createButton)).perform(click());

        Espresso.onView(withId(R.id.nameField)).perform(typeText(createName));
        Espresso.onView(withId(R.id.businessNumberField)).perform(typeText(createBusinessNumber));
        Espresso.onView(withId(R.id.primaryBusinessField)).perform(typeText(createPrimaryBusiness));
        Espresso.onView(withId(R.id.addressField)).perform(typeText(createAddress));
        Espresso.onView(withId(R.id.provinceField)).perform(typeText(createProvince));

        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.createButton)).perform(click());

        int listLength = ((ListView) CRUDTestRule.getActivity().findViewById(R.id.listView))
                .getAdapter().getCount();

        assertEquals(listLength,1);
    }

    @Test
    public void bReadTest(){
        try{Thread.sleep(1000);}catch(Exception e) {}
        Espresso.onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0).perform(click());

        Espresso.onView(withId(R.id.nameFieldDV)).check(matches(withText(createName)));
        Espresso.onView(withId(R.id.businessNumberFieldDV))
                .check(matches(withText(createBusinessNumber)));
        Espresso.onView(withId(R.id.primaryBusinessFieldDV))
                .check(matches(withText(createPrimaryBusiness)));
        Espresso.onView(withId(R.id.addressFieldDV)).check(matches(withText(createAddress)));
        Espresso.onView(withId(R.id.provinceFieldDV)).check(matches(withText(createProvince)));
    }

    @Test
    public void cUpdateTest(){
        try{Thread.sleep(1000);}catch(Exception e) {}
        Espresso.onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0).perform(click());


        Espresso.onView(withId(R.id.nameFieldDV)).perform(replaceText(updateName));

        Espresso.closeSoftKeyboard();

        Espresso.onView(withId(R.id.updateButton)).perform(click());
        try{Thread.sleep(1000);}catch(Exception e) {}
        Espresso.onData(anything()).inAdapterView(withId(R.id.listView))
                .atPosition(0).perform(click());

        Espresso.onView(withId(R.id.nameFieldDV)).check(matches(withText(updateName)));
    }

        @Test
        public void dDeleteTest(){
            try{Thread.sleep(1000);}catch(Exception e) {}
            Espresso.onData(anything()).inAdapterView(withId(R.id.listView))
                    .atPosition(0).perform(click());
            Espresso.onView(withId(R.id.deleteButton)).perform(click());

            int listLength = ((ListView) CRUDTestRule.getActivity().findViewById(R.id.listView))
                    .getAdapter().getCount();

            assertEquals(listLength, 0);
        }

    @After
    public void tearDown() throws Exception {
    }
}

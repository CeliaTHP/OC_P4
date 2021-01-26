package com.meeting_list;

import android.widget.DatePicker;
import android.widget.Toolbar;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.PressBackAction;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;


import com.example.mareu.R;
import com.example.mareu.databinding.ActivityMeetingListBinding;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingService.DummyMeetingsGenerator;
import com.example.mareu.ui.list.MeetingListActivity;
import com.utils.DeleteViewAction;
import com.utils.RecyclerViewItemCountAssertion;

import org.hamcrest.Matchers;
import org.hamcrest.core.AllOf;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;
import static com.utils.RecyclerViewItemCountAssertion.withItemCount;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MeetingListTest {

    private static int ITEMS_COUNT = 11;
    private ActivityMeetingListBinding activityMeetingListBinding;

    private MeetingListActivity listActivity;
    private List<Meeting> meetingList = DummyMeetingsGenerator.generateMeetings(); //public static array instead ?


    @Rule
    public ActivityScenarioRule<MeetingListActivity> mActivityTestRule = new ActivityScenarioRule<>(MeetingListActivity.class);

    @Before
    public void setUp() {
        ActivityScenario scenario = mActivityTestRule.getScenario();
        assertThat(scenario, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least one item
     */
    @Test
    public void myMeetingsList_shouldNotBeEmpty() {
        onView(AllOf.allOf(ViewMatchers.withId(R.id.meeting_recycler_view), isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we click on the menu, filter options are displayed
     */
    @Test
    public void filterOptionsDisplays() {
        //Open the menu
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        // Select the "filter by default" option
        onView(withText("Filtrer par défaut")).perform(click());
        // Verify that our recyclerView displays all meetings (11)
        onView(allOf(withId(R.id.meeting_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        //Open the menu
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        // Select the "filter by date" option
        onView(withText("Filtrer par date")).perform(click());
        // Pick a date
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2021, 3, 19));
        onView(withText("OK")).perform(click());
        // Verify that our recyclerView displays one Meeting
        onView(allOf(withId(R.id.meeting_recycler_view), isDisplayed())).check(withItemCount(1));
        //Open the menu
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().getTargetContext());
        // Select the "filter by room" option
        onView(withText("Filtrer par salle")).perform(click());
        // Select the "PEACH" room
        onView(withText("PEACH")).perform(click());
        // Verify that our recyclerView displays two Meeting
        onView(allOf(withId(R.id.meeting_recycler_view), isDisplayed())).check(withItemCount(2));


    }

    /**
     * When we delete an item, the item is no more shown
     */

    @Test
    public void myMeetingList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(allOf(withId(R.id.meeting_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf(withId(R.id.meeting_recycler_view), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 10
        onView(allOf(withId(R.id.meeting_recycler_view), isDisplayed())).check(withItemCount(ITEMS_COUNT-1));
    }

    //création meeting puis verification qu'il apparait dans la liste






}
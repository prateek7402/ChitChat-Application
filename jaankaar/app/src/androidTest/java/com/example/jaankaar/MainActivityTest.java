package com.example.jaankaar;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> obj=new ActivityTestRule<MainActivity>(MainActivity.class);
    MainActivity mActivity=null;
    @Before
    public void setUp() throws Exception
    {
        mActivity=obj.getActivity();
    }

    @Test
    public void testLaunch()
    {
        View view;
        view=mActivity.findViewById(R.id.show);
        assertNotNull(view);
    }
    @After
    public void tearDown() throws Exception {
            mActivity=null;
    }
}
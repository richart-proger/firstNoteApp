package com.notes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Note that all tests work with an empty DIR directory. Otherwise they fall.
 */
@RunWith(Suite.class)
@SuiteClasses({FileOperationServiceTest.class, NotesManagerTest.class})
public class TestAll {

}

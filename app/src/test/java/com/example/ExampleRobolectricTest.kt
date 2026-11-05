package com.example

import com.example.data.Strings
import com.example.model.AppLanguage
import org.junit.Assert.assertEquals
import org.junit.Test

class ExampleRobolectricTest {

  @Test
  fun `verify navigation strings are localized in English, Hindi, and Telugu`() {
    assertEquals("Home", Strings.navHome.get(AppLanguage.ENGLISH))
    assertEquals("होम", Strings.navHome.get(AppLanguage.HINDI))
    assertEquals("హోమ్", Strings.navHome.get(AppLanguage.TELUGU))

    assertEquals("Explore", Strings.navExplore.get(AppLanguage.ENGLISH))
    assertEquals("खोजें", Strings.navExplore.get(AppLanguage.HINDI))
    assertEquals("అన్వేషించండి", Strings.navExplore.get(AppLanguage.TELUGU))

    assertEquals("Meal Plan", Strings.navPlanner.get(AppLanguage.ENGLISH))
    assertEquals("मील प्लान", Strings.navPlanner.get(AppLanguage.HINDI))
    assertEquals("మీల్ ప్లాన్", Strings.navPlanner.get(AppLanguage.TELUGU))
  }
}

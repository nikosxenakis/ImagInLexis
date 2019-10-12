package com.xenakis;

import com.xenakis.service.Database;
import org.junit.jupiter.api.Test;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseTest {

    @Test
    public void testDB() {

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm");

        Date date = new Date();
        Date time = new Date();
		String strDate = dateFormat.format(date);
		String strTime = timeFormat.format(time);

		Database.insert(ImagInLexis.userName, strTime, strDate, 0, "Αναγνώριση", "Φρούτα");
		Database.insert(ImagInLexis.userName, strTime, strDate, 2, "Αναγνώριση", "Χρώματα");

        List l1 = Database.select("Αναγνώριση", "Φρούτα");
        List l2 = Database.select("Αναγνώριση", "Χρώματα");

        assertEquals(l1.size(), l2.size());
    }
}
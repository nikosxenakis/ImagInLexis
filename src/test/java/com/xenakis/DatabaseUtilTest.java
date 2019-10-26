package com.xenakis;

import com.xenakis.service.ImageUtil;
import com.xenakis.service.ScoreUtil;
import com.xenakis.service.UserUtil;
import org.junit.jupiter.api.Test;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatabaseUtilTest {

    @Test
    void testDB() {

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm");

        Date date = new Date();
        Date time = new Date();
		String strDate = dateFormat.format(date);
		String strTime = timeFormat.format(time);

		ScoreUtil.insertScore(ImagInLexis.userName, strTime, strDate, 0, "Αναγνώριση", "Φρούτα");
        ScoreUtil.insertScore(ImagInLexis.userName, strTime, strDate, 2, "Αναγνώριση", "Χρώματα");

//        List l1 = ScoreUtil.selectScores("Αναγνώριση", "Φρούτα");
//        List l2 = ScoreUtil.selectScores("Αναγνώριση", "Χρώματα");
//
//        assertEquals(l1.size(), l2.size());
    }

    @Test
    void testDBImages() {
//        String path = ImageUtil.getImagePath("logo");
//        assertEquals(path, "images/logo/imagInLexisLogo.png");
    }

}
DELETE FROM screens;
DELETE FROM screenTypes;

INSERT INTO screenTypes VALUES(1, 'mainSreen');
-- INSERT INTO screenTypes VALUES(2, 'testScreen');


INSERT INTO screens VALUES(1, 1, 'fxml/AboutUsMainScreen.fxml', 'AboutUsMainScreen');
INSERT INTO screens VALUES(2, 1, 'fxml/ScoreTableScreen.fxml', 'ScoreTableScreen');
INSERT INTO screens VALUES(3, 1, 'fxml/RecognitionChapterScreen.fxml', 'RecognitionChapterScreen');
INSERT INTO screens VALUES(4, 1, 'fxml/KatonomasiaChapterScreen.fxml', 'KatonomasiaChapterScreen');
INSERT INTO screens VALUES(5, 1, 'fxml/CombinationalChapterScreen.fxml', 'CombinationalChapterScreen');
INSERT INTO screens VALUES(6, 1, 'fxml/MainScreen.fxml', 'MainScreen');
INSERT INTO screens VALUES(7, 1, 'fxml/PreTestScreen.fxml', 'PreTestScreen');
INSERT INTO screens VALUES(8, 1, 'fxml/PostTestScreen.fxml', 'PostTestScreen');
INSERT INTO screens VALUES(9, 1, 'fxml/WhatIsThisMainScreen.fxml', 'WhatIsThisMainScreen');

-- INSERT INTO screens VALUES(1, 2, 'fxml/test.fxml', 'Test');

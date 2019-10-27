DELETE FROM screens;
DELETE FROM screenTypes;

INSERT INTO screenTypes VALUES(1, 'mainSreen');
INSERT INTO screenTypes VALUES(2, 'testScreen');

INSERT INTO screens VALUES(1, 1, 'fxml/AboutUsMainScreen.fxml', 'AboutUsMainScreen');
INSERT INTO screens VALUES(2, 1, 'fxml/ScoreTableScreen.fxml', 'ScoreTableScreen');
INSERT INTO screens VALUES(3, 1, 'fxml/RecognitionChapterScreen.fxml', 'RecognitionChapterScreen');
INSERT INTO screens VALUES(4, 1, 'fxml/KatonomasiaChapterScreen.fxml', 'KatonomasiaChapterScreen');
INSERT INTO screens VALUES(5, 1, 'fxml/CombinationalChapterScreen.fxml', 'CombinationalChapterScreen');
INSERT INTO screens VALUES(6, 1, 'fxml/MainScreen.fxml', 'MainScreen');
INSERT INTO screens VALUES(7, 1, 'fxml/PreTestScreen.fxml', 'PreTestScreen');
INSERT INTO screens VALUES(8, 1, 'fxml/PostTestScreen.fxml', 'PostTestScreen');
INSERT INTO screens VALUES(9, 1, 'fxml/WhatIsThisMainScreen.fxml', 'WhatIsThisMainScreen');

INSERT INTO screens VALUES(10, 2, 'fxml/ChooseImageScreen.fxml', 'chooseImage');
INSERT INTO screens VALUES(11, 2, 'fxml/ChooseImageScreen2.fxml', 'chooseImage2');
INSERT INTO screens VALUES(12, 2, 'fxml/ChooseLabelScreen.fxml', 'chooseLabel');
INSERT INTO screens VALUES(13, 2, 'fxml/ChooseInImageScreen.fxml', 'chooseInImage');
INSERT INTO screens VALUES(14, 2, 'fxml/WhatIsThisScreen.fxml', 'whatIsThis');
INSERT INTO screens VALUES(15, 2, 'fxml/ChooseImageLinkScreen.fxml', 'chooseImageLink');
INSERT INTO screens VALUES(16, 2, 'fxml/ChooseLabelFromSoundScreen.fxml', 'chooseLabelFromSound');

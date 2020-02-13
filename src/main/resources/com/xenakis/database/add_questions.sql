DELETE FROM questions;

CREATE TABLE IF NOT EXISTS "questions" (
	"id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	"chapterId" INTEGER NOT NULL,
	"categoryId" INTEGER NOT NULL,
	"screenTypeId" INTEGER NOT NULL,
	"questionString" TEXT NOT NULL,
	"questionSoundId" INTEGER NOT NULL,
	"answers" TEXT NOT NULL, -- serialized "1,2,3"

	FOREIGN KEY("chapterId") REFERENCES "chapters"("id"),
	FOREIGN KEY("categoryId") REFERENCES "categories"("id"),
	FOREIGN KEY("screenTypeId") REFERENCES "screenTypes"("id"),
	FOREIGN KEY("questionSoundId") REFERENCES "sounds"("id"),

	-- nullable


		-- chooseInImage
			"absolute" TEXT,
			-- circlesList?


		-- chooseLabelFromSound
			"imageId" INTEGER,
			"sound1Id" INTEGER,
			"sound2Id" INTEGER,
			"sound3Id" INTEGER,
			"radioOption1" TEXT,
			"radioOption2" TEXT,
			"radioOption3" TEXT,

			FOREIGN KEY("imageId") REFERENCES "images"("id"),
			FOREIGN KEY("sound1Id") REFERENCES "sounds"("id"),
			FOREIGN KEY("sound2Id") REFERENCES "sounds"("id"),
			FOREIGN KEY("sound3Id") REFERENCES "sounds"("id"),


		-- chooseImage
			"image1Id" INTEGER,
			"image2Id" INTEGER,
			"image3Id" INTEGER,

			FOREIGN KEY("image1Id") REFERENCES "images"("id"),
			FOREIGN KEY("image2Id") REFERENCES "images"("id"),
			FOREIGN KEY("image3Id") REFERENCES "images"("id"),


		-- chooseImage2
			-- +chooseImage fields
			"image4Id" INTEGER,

			FOREIGN KEY("image4Id") REFERENCES "images"("id"),


		-- chooseLabel
			-- image1Id
			-- radioOption1
			-- radioOption2
			-- radioOption3
			-- sound1Id
			-- sound2Id
			-- sound3Id


		-- chooseImageLink
			"bacisImageId" INTEGER,
			-- image1Id
			-- image2Id
			-- image3Id

			FOREIGN KEY("bacisImageId") REFERENCES "images"("id"),


		-- whatIsThis
			-- bacisImageId
);


INSERT INTO screens VALUES(1, 1, 'fxml/AboutUsMainScreen.fxml', 'AboutUsMainScreen');

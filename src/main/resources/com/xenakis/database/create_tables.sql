CREATE TABLE IF NOT EXISTS "sounds" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	"name"	TEXT NOT NULL,
	"path"	TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS "images" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	"name"	TEXT NOT NULL,
	"path"	TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS "chapters" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	"name"	TEXT NOT NULL UNIQUE,
	"greekName"	TEXT UNIQUE
);

CREATE TABLE IF NOT EXISTS "categories" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	"chapterId"	INTEGER NOT NULL,
	"greekName"	TEXT NOT NULL,
	"name"	TEXT,
	FOREIGN KEY("chapterId") REFERENCES "categories"
);

CREATE TABLE IF NOT EXISTS "screenTypes" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	"name"	TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS "screens" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	"screenTypeId"	INTEGER NOT NULL,
	"path"	TEXT NOT NULL,
	"name"	TEXT NOT NULL,
	FOREIGN KEY("screenTypeId") REFERENCES "screenTypes"("id")
);

CREATE TABLE IF NOT EXISTS "users" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	"name"	TEXT NOT NULL,
	"active"	INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS "scores" (
	"id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	"username"	TEXT,
	"time"	TEXT,
	"date"	TEXT,
	"score"	INTEGER,
	"chapterId"	INTEGER,
	"categoryId" INTEGER,
	FOREIGN KEY("chapterId") REFERENCES "chapters"("id"),
	FOREIGN KEY("categoryId") REFERENCES "categories"("id")
);

-- CREATE TABLE IF NOT EXISTS "questions" (
-- 	"id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
-- 	"chapterId" INTEGER NOT NULL,
-- 	"categoryId" INTEGER NOT NULL,
-- 	"screenTypeId" INTEGER NOT NULL,
-- 	"name" TEXT NOT NULL,
-- 	"question" TEXT NOT NULL,

-- 	-- nullable
-- 	"basicImageId" INTEGER,
-- 	"image1Id" INTEGER,
-- 	"image2Id" INTEGER,
-- 	"image3Id" INTEGER,
-- 	"soundId" INTEGER,

-- 	FOREIGN KEY("chapterId") REFERENCES "chapters"("id"),
-- 	FOREIGN KEY("categoryId") REFERENCES "categories"("id"),
-- 	FOREIGN KEY("screenTypeId") REFERENCES "screenTypes"("id"),
-- 	FOREIGN KEY("basicImageId") REFERENCES "images"("id"),
-- 	FOREIGN KEY("image1Id") REFERENCES "images"("id"),
-- 	FOREIGN KEY("image2Id") REFERENCES "images"("id"),
-- 	FOREIGN KEY("image3Id") REFERENCES "images"("id"),
-- 	FOREIGN KEY("soundId") REFERENCES "sounds"("id")
-- );


-- CREATE TABLE IF NOT EXISTS "questionAnswers" (
-- 	"id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
-- 	"questionId" INTEGER NOT NULL,
-- 	"answer" INTEGER NOT NULL,
-- 	FOREIGN KEY("questionId") REFERENCES "questions"("id")
-- );

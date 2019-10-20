DROP TABLE "sounds";

CREATE TABLE "sounds" (
    "id"    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
    "name"  TEXT NOT NULL,
    "path"  TEXT NOT NULL
);

  DELETE 
    FROM
        sounds;
        
    INSERT 
    INTO
        "sounds"
        ("id", "name", "path") 
    VALUES
        (
            1, "completeSound", "sounds/programSounds/completeSound.mp3"
        ), (
            2, "correctSound", "sounds/programSounds/correctSound.mp3"
        ), (
            3, "wrongSound", "sounds/programSounds/wrongSound.mp3"
        ), (
            4, "startProgramSound", "sounds/programSounds/startProgramSound.mp3"
        ), (
            5, "endProgramSound", "sounds/programSounds/endProgramSound.mp3"
        ), (
            6, "chapter1Anagnorisi", "sounds/chapter1/anagnorisi.mp3"
        ), (
            7, "bodySound", "sounds/chapter1/body/tameritouswmatos.mp3"
        ), (
            8, "agkwnas", "sounds/chapter1/body/agkwnas.mp3"
        ), (
            9, "astragalos", "sounds/chapter1/body/astragalos.mp3"
        ), (
            10, "auti", "sounds/chapter1/body/auti.mp3"
        ), (
            11, "dontia", "sounds/chapter1/body/dontia.mp3"
        ), (
            12, "gonato", "sounds/chapter1/body/gonato.mp3"
        ), (
            13, "karpos", "sounds/chapter1/body/karpos.mp3"
        ), (
            14, "koilia", "sounds/chapter1/body/koilia.mp3"
        ), (
            15, "laimos", "sounds/chapter1/body/laimos.mp3"
        ), (
            16, "magoulo", "sounds/chapter1/body/magoulo.mp3"
        ), (
            17, "metwpo", "sounds/chapter1/body/metwpo.mp3"
        ), (
            18, "palami", "sounds/chapter1/body/palami.mp3"
        ), (
            19, "patousa", "sounds/chapter1/body/patousa.mp3"
        ), (
            20, "plath", "sounds/chapter1/body/plath.mp3"
        ), (
            21, "podi", "sounds/chapter1/body/podi.mp3"
        ), (
            22, "sagoni", "sounds/chapter1/body/sagoni.mp3"
        ), (
            23, "stoma", "sounds/chapter1/body/stoma.mp3"
        ), (
            24, "thwrakas", "sounds/chapter1/body/thwrakas.mp3"
        ), (
            25, "xeri", "sounds/chapter1/body/xeri.mp3"
        ), (
            26, "chapter1BodyQuestion1", "sounds/chapter1/body/question1.mp3"
        ), (
            27, "chapter1BodyQuestion2", "sounds/chapter1/body/question2.mp3"
        ), (
            28, "chapter1BodyQuestion3", "sounds/chapter1/body/question3.mp3"
        ), (
            29, "chapter1BodyQuestion4", "sounds/chapter1/body/question4.mp3"
        ), (
            30, "chapter1BodyQuestion5", "sounds/chapter1/body/question5.mp3"
        ), (
            31, "chapter1BodyQuestion6", "sounds/chapter1/body/question6.mp3"
        ), (
            32, "chapter1BodyQuestion7", "sounds/chapter1/body/question7.mp3"
        ), (
            33, "chapter1BodyQuestion8", "sounds/chapter1/body/question8.mp3"
        ), (
            34, "chapter1BodyQuestion9", "sounds/chapter1/body/question9.mp3"
        ), (
            35, "animalsSound", "sounds/chapter1/animals/tazwa.mp3"
        ), (
            36, "chapter1AnagnorisiAgelada", "sounds/chapter1/animals/agelada.mp3"
        ), (
            37, "chapter1AnagnorisiAlepou", "sounds/chapter1/animals/alepou.mp3"
        ), (
            38, "chapter1AnagnorisiFalaina", "sounds/chapter1/animals/falaina.mp3"
        ), (
            39, "chapter1AnagnorisiFokia", "sounds/chapter1/animals/fokia.mp3"
        ), (
            40, "chapter1AnagnorisiGata", "sounds/chapter1/animals/gata.mp3"
        ), (
            41, "chapter1AnagnorisiKanarini", "sounds/chapter1/animals/kanarini.mp3"
        ), (
            42, "chapter1AnagnorisiKatsika", "sounds/chapter1/animals/katsika.mp3"
        ), (
            43, "chapter1AnagnorisiKota", "sounds/chapter1/animals/kota.mp3"
        ), (
            44, "chapter1AnagnorisiLiontari", "sounds/chapter1/animals/liontari.mp3"
        ), (
            45, "chapter1AnagnorisiPapia", "sounds/chapter1/animals/papia.mp3"
        ), (
            46, "chapter1AnagnorisiProvato", "sounds/chapter1/animals/provato.mp3"
        ), (
            47, "chapter1AnagnorisiQuestion1", "sounds/chapter1/animals/question1.mp3"
        ), (
            48, "chapter1AnagnorisiQuestion2", "sounds/chapter1/animals/question2.mp3"
        ), (
            49, "chapter1AnagnorisiQuestion3", "sounds/chapter1/animals/question3.mp3"
        );

INSERT 
    INTO
        "sounds"
        ("id", "name", "path") 
    VALUES
        (
            50, "chapter1AnagnorisiQuestion4", "sounds/chapter1/animals/question4.mp3"
        ), (
            51, "chapter1AnagnorisiQuestion5", "sounds/chapter1/animals/question5.mp3"
        ), (
            52, "chapter1AnagnorisiQuestion6", "sounds/chapter1/animals/question6.mp3"
        ), (
            53, "chapter1AnagnorisiSkilos", "sounds/chapter1/animals/skilos.mp3"
        ), (
            54, "chapter1AnagnorisiThalasioselefantas", "sounds/chapter1/animals/thalasioselefantas.mp3"
        ), (
            55, "chapter1AnagnorisiTigris", "sounds/chapter1/animals/tigris.mp3"
        ), (
            56, "fruitsSound", "sounds/chapter1/fruits/tafroutakaitalaxanika.mp3"
        ), (
            57, "aggouria", "sounds/chapter1/fruits/aggouria.mp3"
        ), (
            58, "fraoula", "sounds/chapter1/fruits/fraoula.mp3"
        ), (
            59, "kerasi", "sounds/chapter1/fruits/kerasi.mp3"
        ), (
            60, "kolokithia", "sounds/chapter1/fruits/kolokithia.mp3"
        ), (
            61, "laxano", "sounds/chapter1/fruits/laxano.mp3"
        ), (
            62, "marouli", "sounds/chapter1/fruits/marouli.mp3"
        ), (
            63, "milo", "sounds/chapter1/fruits/milo.mp3"
        ), (
            64, "mpamies", "sounds/chapter1/fruits/mpamies.mp3"
        ), (
            65, "chapter1Fruitsquestion1", "sounds/chapter1/fruits/question1.mp3"
        ), (
            66, "chapter1Fruitsquestion2", "sounds/chapter1/fruits/question2.mp3"
        ), (
            67, "chapter1Fruitsquestion3", "sounds/chapter1/fruits/question3.mp3"
        ), (
            68, "chapter1Fruitsquestion4", "sounds/chapter1/fruits/question4.mp3"
        ), (
            69, "chapter1Fruitsquestion5", "sounds/chapter1/fruits/question5.mp3"
        ), (
            70, "rodakino", "sounds/chapter1/fruits/rodakino.mp3"
        ), (
            71, "rodi", "sounds/chapter1/fruits/rodi.mp3"
        ), (
            72, "selino", "sounds/chapter1/fruits/selino.mp3"
        ), (
            73, "spanaki", "sounds/chapter1/fruits/spanaki.mp3"
        ), (
            74, "sparaggia", "sounds/chapter1/fruits/sparaggia.mp3"
        );

INSERT 
    INTO
        "sounds"
        ("id", "name", "path") 
    VALUES
        (
            75, "vatomouro", "sounds/chapter1/fruits/vatomouro.mp3"
        ), (
            76, "objectSound", "sounds/chapter1/objects/taantikeimena.mp3"
        ), (
            77, "biblio", "sounds/chapter1/objects/biblio.mp3"
        ), (
            78, "farasi", "sounds/chapter1/objects/farasi.mp3"
        ), (
            79, "kathreftis", "sounds/chapter1/objects/kathreftis.mp3"
        ), (
            80, "katsarola", "sounds/chapter1/objects/katsarola.mp3"
        ), (
            81, "kopidi", "sounds/chapter1/objects/kopidi.mp3"
        ), (
            82, "korniza", "sounds/chapter1/objects/korniza.mp3"
        ), (
            83, "koupa", "sounds/chapter1/objects/koupa.mp3"
        ), (
            84, "lekani", "sounds/chapter1/objects/lekani.mp3"
        ), (
            85, "maxairi", "sounds/chapter1/objects/maxairi.mp3"
        ), (
            86, "pinakas", "sounds/chapter1/objects/pinakas.mp3"
        ), (
            87, "psalidi", "sounds/chapter1/objects/psalidi.mp3"
        ), (
            88, "chapter1Objectsquestion1", "sounds/chapter1/objects/question1.mp3"
        ), (
            89, "chapter1Objectsquestion2", "sounds/chapter1/objects/question2.mp3"
        ), (
            90, "chapter1Objectsquestion3", "sounds/chapter1/objects/question3.mp3"
        ), (
            91, "chapter1Objectsquestion4", "sounds/chapter1/objects/question4.mp3"
        ), (
            92, "chapter1Objectsquestion5", "sounds/chapter1/objects/question5.mp3"
        ), (
            93, "chapter1Objectsquestion6", "sounds/chapter1/objects/question6.mp3"
        ), (
            94, "sfouggaristra", "sounds/chapter1/objects/sfouggaristra.mp3"
        ), (
            95, "skoupa", "sounds/chapter1/objects/skoupa.mp3"
        ), (
            96, "objectsSound", "sounds/chapter1/objects/taantikeimena.mp3"
        ), (
            97, "tsoygkrana", "sounds/chapter1/objects/tsoygkrana.mp3"
        ), (
            98, "galazio", "sounds/chapter1/colors/galazio.mp3"
        ), (
            99, "kafe", "sounds/chapter1/colors/kafe.mp3"
        );
    INSERT 
    INTO
        "sounds"
        ("id", "name", "path") 
    VALUES
        (
            100, "kitrino", "sounds/chapter1/colors/kitrino.mp3"
        ), (
            101, "kokkino", "sounds/chapter1/colors/kokkino.mp3"
        ), (
            102, "ladi", "sounds/chapter1/colors/ladi.mp3"
        ), (
            103, "mov", "sounds/chapter1/colors/mov.mp3"
        ), (
            104, "mple", "sounds/chapter1/colors/mple.mp3"
        ), (
            105, "portokali", "sounds/chapter1/colors/portokali.mp3"
        ), (
            106, "prasino", "sounds/chapter1/colors/prasino.mp3"
        ), (
            107, "chapter1Colorsquestion1", "sounds/chapter1/colors/question1.mp3"
        ), (
            108, "chapter1Colorsquestion2", "sounds/chapter1/colors/question2.mp3"
        ), (
            109, "chapter1Colorsquestion3", "sounds/chapter1/colors/question3.mp3"
        ), (
            110, "chapter1Colorsquestion4", "sounds/chapter1/colors/question4.mp3"
        ), (
            111, "chapter1Colorsquestion5", "sounds/chapter1/colors/question5.mp3"
        ), (
            112, "colorSound", "sounds/chapter1/colors/taxrwmata.mp3"
        ), (
            113, "xriso", "sounds/chapter1/colors/xriso.mp3"
        ), (
            114, "chapter1ShapeQuestion1", "sounds/chapter1/shape/question1.mp3"
        ), (
            115, "chapter1ShapeQuestion2", "sounds/chapter1/shape/question2.mp3"
        ), (
            116, "chapter1ShapeQuestion3", "sounds/chapter1/shape/question3.mp3"
        ), (
            117, "chapter1ShapeQuestion4", "sounds/chapter1/shape/question4.mp3"
        ), (
            118, "chapter1ShapeQuestion5", "sounds/chapter1/shape/question5.mp3"
        ), (
            119, "chapter1ShapeQuestion6", "sounds/chapter1/shape/question6.mp3"
        ), (
            120, "boukali", "sounds/chapter1/shape/boukali.mp3"
        ), (
            121, "kilindros", "sounds/chapter1/shape/kilindros.mp3"
        ), (
            122, "kivos", "sounds/chapter1/shape/kivos.mp3"
        ), (
            123, "orthogwnio", "sounds/chapter1/shape/orthogwnio.mp3"
        ), (
            124, "piramida", "sounds/chapter1/shape/piramida.mp3"
        ), (
            125, "poligwno", "sounds/chapter1/shape/poligwno.mp3"
        ), (
            126, "romvos", "sounds/chapter1/shape/romvos.mp3"
        ), (
            127, "shapeSound", "sounds/chapter1/shape/tasximata.mp3"
        ), (
            128, "tetragwno", "sounds/chapter1/shape/tetragwno.mp3"
        ), (
            129, "trigwno", "sounds/chapter1/shape/trigwno.mp3"
        ), (
            130, "professionsSound", "sounds/chapter1/professions/taepaggelmata.mp3"
        ), (
            131, "akrovaths", "sounds/chapter1/professions/akrovaths.mp3"
        ), (
            132, "diths", "sounds/chapter1/professions/diths.mp3"
        ), (
            133, "ithopoios", "sounds/chapter1/professions/ithopoios.mp3"
        ), (
            134, "kamariera", "sounds/chapter1/professions/kamariera.mp3"
        ), (
            135, "kinigos", "sounds/chapter1/professions/kinigos.mp3"
        ), (
            136, "kolimviths", "sounds/chapter1/professions/kolimviths.mp3"
        ), (
            137, "ktinotrofos", "sounds/chapter1/professions/ktinotrofos.mp3"
        ), (
            138, "modistra", "sounds/chapter1/professions/modistra.mp3"
        ), (
            139, "nosokoma", "sounds/chapter1/professions/nosokoma.mp3"
        ), (
            140, "politria", "sounds/chapter1/professions/politria.mp3"
        ), (
            141, "psaras", "sounds/chapter1/professions/psaras.mp3"
        ), (
            142, "stratioths", "sounds/chapter1/professions/stratioths.mp3"
        ), (
            143, "tragoydistria", "sounds/chapter1/professions/tragoydistria.mp3"
        ), (
            144, "xoreutria", "sounds/chapter1/professions/xoreutria.mp3"
        ), (
            145, "chapter1ProfessionsQuestion1", "sounds/chapter1/professions/question1.mp3"
        ), (
            146, "chapter1ProfessionsQuestion2", "sounds/chapter1/professions/question2.mp3"
        ), (
            147, "chapter1ProfessionsQuestion3", "sounds/chapter1/professions/question3.mp3"
        ), (
            148, "chapter1ProfessionsQuestion4", "sounds/chapter1/professions/question4.mp3"
        ), (
            149, "chapter1ProfessionsQuestion5", "sounds/chapter1/professions/question5.mp3"
        ), (
            150, "chapter1ProfessionsQuestion6", "sounds/chapter1/professions/question6.mp3"
        ), (
            151, "atmosidero", "sounds/chapter1/appliancies/atmosidero.mp3"
        ), (
            152, "hlektrikhskoupa", "sounds/chapter1/appliancies/hlektrikhskoupa.mp3"
        ), (
            153, "kouzina", "sounds/chapter1/appliancies/kouzina.mp3"
        ), (
            154, "mixanhgkazon", "sounds/chapter1/appliancies/mixanhgkazon.mp3"
        ), (
            155, "ntoylapa", "sounds/chapter1/appliancies/ntoylapa.mp3"
        ), (
            156, "applianciesSound", "sounds/chapter1/appliancies/oihlektrikessuskeues.mp3"
        ), (
            157, "pistolaki", "sounds/chapter1/appliancies/pistolaki.mp3"
        ), (
            158, "plhntiriorouxwn", "sounds/chapter1/appliancies/plhntiriorouxwn.mp3"
        ), (
            159, "plinthriopiatwn", "sounds/chapter1/appliancies/plinthriopiatwn.mp3"
        ), (
            160, "porta", "sounds/chapter1/appliancies/porta.mp3"
        ), (
            161, "psigeio", "sounds/chapter1/appliancies/psigeio.mp3"
        ), (
            162, "sidero", "sounds/chapter1/appliancies/sidero.mp3"
        ), (
            163, "siderwstra", "sounds/chapter1/appliancies/siderwstra.mp3"
        ), (
            164, "tostiera", "sounds/chapter1/appliancies/tostiera.mp3"
        ), (
            165, "chapter1ApplianciesQuestion1", "sounds/chapter1/appliancies/question1.mp3"
        ), (
            166, "chapter1ApplianciesQuestion2", "sounds/chapter1/appliancies/question2.mp3"
        ), (
            167, "chapter1ApplianciesQuestion3", "sounds/chapter1/appliancies/question3.mp3"
        ), (
            168, "chapter1ApplianciesQuestion4", "sounds/chapter1/appliancies/question4.mp3"
        ), (
            169, "chapter1ApplianciesQuestion5", "sounds/chapter1/appliancies/question5.mp3"
        ), (
            170, "chapter1ApplianciesQuestion6", "sounds/chapter1/appliancies/question6.mp3"
        ), (
            171, "dasos", "sounds/chapter1/location/dasos.mp3"
        ), (
            172, "gipedo", "sounds/chapter1/location/gipedo.mp3"
        ), (
            173, "khpos", "sounds/chapter1/location/khpos.mp3"
        ), (
            174, "ksenodoxeio", "sounds/chapter1/location/ksenodoxeio.mp3"
        ), (
            175, "lounapark", "sounds/chapter1/location/lounapark.mp3"
        ), (
            176, "manabiko", "sounds/chapter1/location/manabiko.mp3"
        ), (
            177, "nosokomeio", "sounds/chapter1/location/nosokomeio.mp3"
        ), (
            178, "paidikhxara", "sounds/chapter1/location/paidikhxara.mp3"
        ), (
            179, "pediada", "sounds/chapter1/location/pediada.mp3"
        ), (
            180, "psilikatzidiko", "sounds/chapter1/location/psilikatzidiko.mp3"
        ), (
            181, "sxoleio", "sounds/chapter1/location/sxoleio.mp3"
        ), (
            182, "locationSound", "sounds/chapter1/location/taktiriakaioitopothesies.mp3"
        ), (
            183, "tameio", "sounds/chapter1/location/tameio.mp3"
        ), (
            184, "xwrafi", "sounds/chapter1/location/xwrafi.mp3"
        ), (
            185, "chapter1LocationQuestion1", "sounds/chapter1/location/question1.mp3"
        ), (
            186, "chapter1LocationQuestion2", "sounds/chapter1/location/question2.mp3"
        ), (
            187, "chapter1LocationQuestion3", "sounds/chapter1/location/question3.mp3"
        ), (
            188, "chapter1LocationQuestion4", "sounds/chapter1/location/question4.mp3"
        ), (
            189, "chapter1LocationQuestion5", "sounds/chapter1/location/question5.mp3"
        ), (
            190, "chapter1LocationQuestion6", "sounds/chapter1/location/question6.mp3"
        ), (
            191, "aeroplano", "sounds/chapter1/car/aeroplano.mp3"
        ), (
            192, "aerostato", "sounds/chapter1/car/aerostato.mp3"
        ), (
            193, "aporimmatoforo", "sounds/chapter1/car/aporimmatoforo.mp3"
        ), (
            194, "astikoleoforeio", "sounds/chapter1/car/astikoleoforeio.mp3"
        ), (
            195, "bagoni", "sounds/chapter1/car/bagoni.mp3"
        ), (
            196, "elikoptero", "sounds/chapter1/car/elikoptero.mp3"
        ), (
            197, "forthgo", "sounds/chapter1/car/forthgo.mp3"
        ), (
            198, "lewforeio", "sounds/chapter1/car/lewforeio.mp3"
        ), (
            199, "mixani", "sounds/chapter1/car/mixani.mp3"
        );

INSERT 
    INTO
        "sounds"
        ("id", "name", "path") 
    VALUES 
        (
            200, "patini", "sounds/chapter1/car/patini.mp3"
        ), (
            201, "podilato", "sounds/chapter1/car/podilato.mp3"
        ), (
            202, "purosvestikooxima", "sounds/chapter1/car/purosvestikooxima.mp3"
        ), (
            203, "carSound", "sounds/chapter1/car/tamesametaforaskaitaoximata.mp3"
        ), (
            204, "traino", "sounds/chapter1/car/traino.mp3"
        ), (
            205, "trikuklo", "sounds/chapter1/car/trikuklo.mp3"
        ), (
            206, "chapter1CarQuestion1", "sounds/chapter1/car/question1.mp3"
        ), (
            207, "chapter1CarQuestion2", "sounds/chapter1/car/question2.mp3"
        ), (
            208, "chapter1CarQuestion3", "sounds/chapter1/car/question3.mp3"
        ), (
            209, "chapter1CarQuestion4", "sounds/chapter1/car/question4.mp3"
        ), (
            210, "chapter1CarQuestion5", "sounds/chapter1/car/question5.mp3"
        ), (
            211, "chapter1CarQuestion6", "sounds/chapter1/car/question6.mp3"
        ), (
            212, "actionSound", "sounds/chapter1/action/energies.mp3"
        ), (
            213, "kaneieleytherhptwsh", "sounds/chapter1/action/kaneieleytherhptwsh.mp3"
        ), (
            214, "kaneiistioploia", "sounds/chapter1/action/kaneiistioploia.mp3"
        ), (
            215, "kaneiski", "sounds/chapter1/action/kaneiski.mp3"
        ), (
            216, "katharizei", "sounds/chapter1/action/katharizei.mp3"
        ), (
            217, "kseskonizei", "sounds/chapter1/action/kseskonizei.mp3"
        ), (
            218, "mageireyei", "sounds/chapter1/action/mageireyei.mp3"
        ), (
            219, "mastoreuei", "sounds/chapter1/action/mastoreuei.mp3"
        ), (
            220, "energies", "sounds/chapter1/action/energies.mp3"
        ), (
            221, "paeivolta", "sounds/chapter1/action/paeivolta.mp3"
        ), (
            222, "peftei", "sounds/chapter1/action/peftei.mp3"
        ), (
            223, "plekei", "sounds/chapter1/action/plekei.mp3"
        ), (
            224, "skarfalwnei", "sounds/chapter1/action/skarfalwnei.mp3"
        ), (
            225, "vafei", "sounds/chapter1/action/vafei.mp3"
        ), (
            226, "xtizei", "sounds/chapter1/action/xtizei.mp3"
        ), (
            227, "kaneieleytherhptwsh", "sounds/chapter1/action/kaneieleytherhptwsh.mp3"
        ), (
            228, "chapter1ActionQuestion1", "sounds/chapter1/action/question1.mp3"
        ), (
            229, "chapter1ActionQuestion2", "sounds/chapter1/action/question2.mp3"
        ), (
            230, "chapter1ActionQuestion3", "sounds/chapter1/action/question3.mp3"
        ), (
            231, "chapter1ActionQuestion4", "sounds/chapter1/action/question4.mp3"
        ), (
            232, "chapter1ActionQuestion5", "sounds/chapter1/action/question5.mp3"
        ), (
            233, "chapter1ActionQuestion6", "sounds/chapter1/action/question6.mp3"
        ), (
            234, "agxomenos", "sounds/chapter1/emotion/agxomenos.mp3"
        ), (
            235, "aidiasmenos", "sounds/chapter1/emotion/aidiasmenos.mp3"
        ), (
            236, "ekpliktos", "sounds/chapter1/emotion/ekpliktos.mp3"
        ), (
            237, "lipimenos", "sounds/chapter1/emotion/lipimenos.mp3"
        ), (
            238, "emotionSound", "sounds/chapter1/emotion/tasunaisthimata.mp3"
        ), (
            239, "xaroumenos", "sounds/chapter1/emotion/xaroumenos.mp3"
        ), (
            240, "zhliaris", "sounds/chapter1/emotion/zhliaris.mp3"
        ), (
            241, "thimwmenos", "sounds/chapter1/emotion/thimwmenos.mp3"
        ), (
            242, "chapter1EmotionQuestion1", "sounds/chapter1/emotion/question1.mp3"
        ), (
            243, "chapter1EmotionQuestion2", "sounds/chapter1/emotion/question2.mp3"
        ), (
            244, "chapter1EmotionQuestion3", "sounds/chapter1/emotion/question3.mp3"
        ), (
            245, "chapter1EmotionQuestion4", "sounds/chapter1/emotion/question4.mp3"
        ), (
            246, "chapter1EmotionQuestion5", "sounds/chapter1/emotion/question5.mp3"
        ), (
            247, "seasonSound", "sounds/chapter1/season/oiepoxes.mp3"
        ), (
            248, "anoiksh", "sounds/chapter1/season/anoiksh.mp3"
        ), (
            249, "dasos", "sounds/chapter1/season/dasos.mp3"
        ), (
            250, "dekemvris", "sounds/chapter1/season/dekemvris.mp3"
        ), (
            251, "fthinopwro", "sounds/chapter1/season/fthinopwro.mp3"
        ), (
            252, "kalokairi", "sounds/chapter1/season/kalokairi.mp3"
        ), (
            253, "louloudia", "sounds/chapter1/season/louloudia.mp3"
        ), (
            254, "paralia", "sounds/chapter1/season/paralia.mp3"
        ), (
            255, "thalassa", "sounds/chapter1/season/thalassa.mp3"
        ), (
            256, "vroxes", "sounds/chapter1/season/vroxes.mp3"
        ), (
            257, "xeimwnas", "sounds/chapter1/season/xeimwnas.mp3"
        ), (
            258, "xioni", "sounds/chapter1/season/xioni.mp3"
        ), (
            259, "chapter1SeasonQuestion1", "sounds/chapter1/season/question1.mp3"
        ), (
            260, "chapter1SeasonQuestion2", "sounds/chapter1/season/question2.mp3"
        ), (
            261, "chapter1SeasonQuestion3", "sounds/chapter1/season/question3.mp3"
        ), (
            262, "chapter1SeasonQuestion4", "sounds/chapter1/season/question4.mp3"
        ), (
            263, "chapter1SeasonQuestion5", "sounds/chapter1/season/question5.mp3"
        ), (
            264, "aprilio", "sounds/chapter1/month/aprilio.mp3"
        ), (
            265, "apriliou", "sounds/chapter1/month/apriliou.mp3"
        ), (
            266, "dekemvrhs", "sounds/chapter1/month/dekemvrhs.mp3"
        ), (
            267, "dekemvrio", "sounds/chapter1/month/dekemvrio.mp3"
        ), (
            268, "fevrouario", "sounds/chapter1/month/fevrouario.mp3"
        ), (
            269, "fevrouarios", "sounds/chapter1/month/fevrouarios.mp3"
        ), (
            270, "ianouario", "sounds/chapter1/month/ianouario.mp3"
        ), (
            271, "ianouarios", "sounds/chapter1/month/ianouarios.mp3"
        ), (
            272, "ioulio", "sounds/chapter1/month/ioulio.mp3"
        ), (
            273, "iounio", "sounds/chapter1/month/iounio.mp3"
        ), (
            274, "iouniou", "sounds/chapter1/month/iouniou.mp3"
        ), (
            275, "maio", "sounds/chapter1/month/maio.mp3"
        ), (
            276, "maiou", "sounds/chapter1/month/maiou.mp3"
        ), (
            277, "martio", "sounds/chapter1/month/martio.mp3"
        ), (
            278, "martiou", "sounds/chapter1/month/martiou.mp3"
        ), (
            279, "noemmvriou", "sounds/chapter1/month/noemmvriou.mp3"
        ), (
            280, "noemvrio", "sounds/chapter1/month/noemvrio.mp3"
        ), (
            281, "noemvrios", "sounds/chapter1/month/noemvrios.mp3"
        ), (
            282, "monthSound", "sounds/chapter1/month/oimhnes.mp3"
        ), (
            283, "oktombrios", "sounds/chapter1/month/oktombrios.mp3"
        ), (
            284, "oktvmvriou", "sounds/chapter1/month/oktvmvriou.mp3"
        ), (
            285, "semptembrio", "sounds/chapter1/month/semptembrio.mp3"
        ), (
            286, "semptemvrios", "sounds/chapter1/month/semptemvrios.mp3"
        ), (
            287, "chapter1MonthQuestion1", "sounds/chapter1/month/question1.mp3"
        ), (
            288, "chapter1MonthQuestion2", "sounds/chapter1/month/question2.mp3"
        ), (
            289, "chapter1MonthQuestion3", "sounds/chapter1/month/question3.mp3"
        ), (
            290, "chapter1MonthQuestion4", "sounds/chapter1/month/question4.mp3"
        ), (
            291, "chapter1MonthQuestion5", "sounds/chapter1/month/question5.mp3"
        ), (
            292, "chapter1MonthQuestion6", "sounds/chapter1/month/question6.mp3"
        ), (
            293, "chapter1MonthQuestion7", "sounds/chapter1/month/question7.mp3"
        ), (
            294, "chapter1MonthQuestion8", "sounds/chapter1/month/question8.mp3"
        ), (
            295, "chapter1MonthQuestion9", "sounds/chapter1/month/question9.mp3"
        ), (
            296, "chapter1MonthQuestion10", "sounds/chapter1/month/question10.mp3"
        ), (
            297, "chapter1MonthQuestion11", "sounds/chapter1/month/question11.mp3"
        ), (
            298, "chapter1MonthQuestion12", "sounds/chapter1/month/question12.mp3"
        ), (
            299, "chapter1MonthQuestion13", "sounds/chapter1/month/question13.mp3"
        );

INSERT 
    INTO
        "sounds"
        ("id", "name", "path") 
    VALUES
        (
            300, "deutera", "sounds/chapter1/week/deutera.mp3"
        ), (
            301, "kuriakh", "sounds/chapter1/week/kuriakh.mp3"
        ), (
            302, "weekSound", "sounds/chapter1/week/oihmeresthsevdomadas.mp3"
        ), (
            303, "paraskeuh", "sounds/chapter1/week/paraskeuh.mp3"
        ), (
            304, "pempth", "sounds/chapter1/week/pempth.mp3"
        ), (
            305, "savvato", "sounds/chapter1/week/savvato.mp3"
        ), (
            306, "tetarth", "sounds/chapter1/week/tetarth.mp3"
        ), (
            307, "trith", "sounds/chapter1/week/trith.mp3"
        ), (
            308, "chapter1WeekProposal1", "sounds/chapter1/week/proposal1.mp3"
        ), (
            309, "chapter1WeekProposal2", "sounds/chapter1/week/proposal2.mp3"
        ), (
            310, "chapter1WeekProposal3", "sounds/chapter1/week/proposal3.mp3"
        ), (
            311, "chapter1WeekProposal4", "sounds/chapter1/week/proposal4.mp3"
        ), (
            312, "chapter1WeekProposal5", "sounds/chapter1/week/proposal5.mp3"
        ), (
            313, "chapter1WeekQuestion1", "sounds/chapter1/week/question1.mp3"
        ), (
            314, "chapter1WeekQuestion2", "sounds/chapter1/week/question2.mp3"
        ), (
            315, "deuteratetarth", "sounds/chapter1/week/1/deuteratetarth.mp3"
        ), (
            316, "deuteratrith", "sounds/chapter1/week/1/deuteratrith.mp3"
        ), (
            317, "trithdeutera", "sounds/chapter1/week/1/trithdeutera.mp3"
        ), (
            318, "pempthparaskeuh", "sounds/chapter1/week/2/pempthparaskeuh.mp3"
        ), (
            319, "pempthsavvato", "sounds/chapter1/week/2/pempthsavvato.mp3"
        ), (
            320, "savvatodeutera", "sounds/chapter1/week/2/savvatodeutera.mp3"
        ), (
            321, "savvatokuriakh", "sounds/chapter1/week/3/savvatokuriakh.mp3"
        ), (
            322, "savvatoparaskeuh", "sounds/chapter1/week/3/savvatoparaskeuh.mp3"
        ), (
            323, "trithpempth", "sounds/chapter1/week/3/trithpempth.mp3"
        ), (
            324, "deuteratetarth", "sounds/chapter1/week/4/deuteratetarth.mp3"
        ), (
            325, "tetarthpempth", "sounds/chapter1/week/4/tetarthpempth.mp3"
        ), (
            326, "tetarthtrith", "sounds/chapter1/week/4/tetarthtrith.mp3"
        ), (
            327, "trithdeutera", "sounds/chapter1/week/5/trithdeutera.mp3"
        ), (
            328, "trithsavvato", "sounds/chapter1/week/5/trithsavvato.mp3"
        ), (
            329, "trithtetarth", "sounds/chapter1/week/5/trithtetarth.mp3"
        ), (
            330, "paraskeuhpempth", "sounds/chapter1/week/6/paraskeuhpempth.mp3"
        ), (
            331, "paraskeuhsavvato", "sounds/chapter1/week/6/paraskeuhsavvato.mp3"
        ), (
            332, "savvatoparaskeuh", "sounds/chapter1/week/6/savvatoparaskeuh.mp3"
        ), (
            333, "hkasetinaeinai", "sounds/chapter1/opposites/hkasetinaeinai.mp3"
        ), (
            334, "hkasetinaeinai1", "sounds/chapter1/opposites/hkasetinaeinai1.mp3"
        ), (
            335, "hkasetinaeinai2", "sounds/chapter1/opposites/hkasetinaeinai2.mp3"
        ), (
            336, "hkasetinaeinai3", "sounds/chapter1/opposites/hkasetinaeinai3.mp3"
        ), (
            337, "hkornizaeinai", "sounds/chapter1/opposites/hkornizaeinai.mp3"
        ), (
            338, "hkornizaeinai1", "sounds/chapter1/opposites/hkornizaeinai1.mp3"
        ), (
            339, "hkornizaeinai2", "sounds/chapter1/opposites/hkornizaeinai2.mp3"
        ), (
            340, "hkornizaeinai3", "sounds/chapter1/opposites/hkornizaeinai3.mp3"
        ), (
            341, "oppositesSound", "sounds/chapter1/opposites/oiantithetikoiprosdiorismoi.mp3"
        ), (
            342, "chapter1OppositesQuestion1", "sounds/chapter1/opposites/question1.mp3"
        ), (
            343, "chapter1OppositesQuestion2", "sounds/chapter1/opposites/question2.mp3"
        ), (
            344, "toenapothrieinai", "sounds/chapter1/opposites/toenapothrieinai.mp3"
        ), (
            345, "toenapothrieinai1", "sounds/chapter1/opposites/toenapothrieinai1.mp3"
        ), (
            346, "toenapothrieinai2", "sounds/chapter1/opposites/toenapothrieinai2.mp3"
        ), (
            347, "toenapothrieinai3", "sounds/chapter1/opposites/toenapothrieinai3.mp3"
        ), (
            348, "tompoukalieinai", "sounds/chapter1/opposites/tompoukalieinai.mp3"
        ), (
            349, "tompoukalieinai1", "sounds/chapter1/opposites/tompoukalieinai1.mp3"
        ), (
            350, "tompoukalieinai2", "sounds/chapter1/opposites/tompoukalieinai2.mp3"
        ), (
            351, "tompoukalieinai3", "sounds/chapter1/opposites/tompoukalieinai3.mp3"
        ), (
            352, "substractiveAbillitySound", "sounds/chapter1/substractiveAbillity/hafairetikhikanothta.mp3"
        ), (
            353, "chapter1SubstractiveAbillityQuestion1", "sounds/chapter1/substractiveAbillity/question1.mp3"
        ), (
            354, "automatedActionsSound", "sounds/chapter1/automatedActions/oiautomatopoihmenesprakseis.mp3"
        ), (
            355, "closedQuestionsSound", "sounds/chapter1/automatedActions/erwthseisKleistoutupou.mp3"
        ), (
            356, "chapter1AutomatedActionsQuestion1", "sounds/chapter1/automatedActions/question1.mp3"
        ), (
            357, "chapter1AutomatedActionsQuestion2", "sounds/chapter1/automatedActions/question2.mp3"
        ), (
            358, "aulh", "sounds/chapter1/automatedActions/task1/aulh.mp3"
        ), (
            359, "diskos", "sounds/chapter1/automatedActions/task1/diskos.mp3"
        ), (
            360, "faghto", "sounds/chapter1/automatedActions/task1/faghto.mp3"
        ), (
            361, "krevati", "sounds/chapter1/automatedActions/task1/krevati.mp3"
        ), (
            362, "maxairopirouna", "sounds/chapter1/automatedActions/task1/maxairopirouna.mp3"
        ), (
            363, "mpaniera", "sounds/chapter1/automatedActions/task1/mpaniera.mp3"
        ), (
            364, "nero", "sounds/chapter1/automatedActions/task1/nero.mp3"
        ), (
            365, "odontovourtsa", "sounds/chapter1/automatedActions/task1/odontovourtsa.mp3"
        ), (
            366, "pagos", "sounds/chapter1/automatedActions/task1/pagos.mp3"
        ), (
            367, "piato", "sounds/chapter1/automatedActions/task1/piato.mp3"
        ), (
            368, "pistolaki", "sounds/chapter1/automatedActions/task1/pistolaki.mp3"
        ), (
            369, "pothri", "sounds/chapter1/automatedActions/task1/pothri.mp3"
        ), (
            370, "sfouggari", "sounds/chapter1/automatedActions/task1/sfouggari.mp3"
        ), (
            371, "skampo", "sounds/chapter1/automatedActions/task1/skampo.mp3"
        ), (
            372, "tigani", "sounds/chapter1/automatedActions/task1/tigani.mp3"
        ), (
            373, "trapezi", "sounds/chapter1/automatedActions/task1/trapezi.mp3"
        ), (
            374, "tsougkrana", "sounds/chapter1/automatedActions/task1/tsougkrana.mp3"
        ), (
            375, "vazo", "sounds/chapter1/automatedActions/task1/vazo.mp3"
        ), (
            376, "vareli", "sounds/chapter1/automatedActions/task1/vareli.mp3"
        ), (
            377, "veranta", "sounds/chapter1/automatedActions/task1/veranta.mp3"
        ), (
            378, "vourtsa", "sounds/chapter1/automatedActions/task1/vourtsa.mp3"
        ), (
            379, "xtena", "sounds/chapter1/automatedActions/task1/xtena.mp3"
        ), (
            380, "chapter1AutomatedActionsTask1Proposal1", "sounds/chapter1/automatedActions/task1/proposal1.mp3"
        ), (
            381, "chapter1AutomatedActionsTask1Proposal2", "sounds/chapter1/automatedActions/task1/proposal2.mp3"
        ), (
            382, "chapter1AutomatedActionsTask1Proposal3", "sounds/chapter1/automatedActions/task1/proposal3.mp3"
        ), (
            383, "chapter1AutomatedActionsTask1Proposal4", "sounds/chapter1/automatedActions/task1/proposal4.mp3"
        ), (
            384, "chapter1AutomatedActionsTask1Proposal5", "sounds/chapter1/automatedActions/task1/proposal5.mp3"
        ), (
            385, "chapter1AutomatedActionsTask1Proposal6", "sounds/chapter1/automatedActions/task1/proposal6.mp3"
        ), (
            386, "chapter1AutomatedActionsTask1Proposal7", "sounds/chapter1/automatedActions/task1/proposal7.mp3"
        ), (
            387, "chapter1AutomatedActionsTask1Proposal8", "sounds/chapter1/automatedActions/task1/proposal8.mp3"
        ), (
            388, "chapter1AutomatedActionsTask2Proposal1", "sounds/chapter1/automatedActions/task2/proposal1.mp3"
        ), (
            389, "chapter1AutomatedActionsTask2Proposal2", "sounds/chapter1/automatedActions/task2/proposal2.mp3"
        ), (
            390, "chapter1AutomatedActionsTask2Proposal3", "sounds/chapter1/automatedActions/task2/proposal3.mp3"
        ), (
            391, "chapter1AutomatedActionsTask2Proposal4", "sounds/chapter1/automatedActions/task2/proposal4.mp3"
        ), (
            392, "chapter1AutomatedActionsTask2Proposal5", "sounds/chapter1/automatedActions/task2/proposal5.mp3"
        ), (
            393, "chapter1AutomatedActionsTask2Proposal6", "sounds/chapter1/automatedActions/task2/proposal6.mp3"
        ), (
            394, "chapter1AutomatedActionsTask2Proposal7", "sounds/chapter1/automatedActions/task2/proposal7.mp3"
        ), (
            395, "chapter1AutomatedActionsTask2Proposal8", "sounds/chapter1/automatedActions/task2/proposal8.mp3"
        ), (
            396, "chapter1AutomatedActionsTask2Proposal9", "sounds/chapter1/automatedActions/task2/proposal9.mp3"
        ), (
            397, "chapter1AutomatedActionsTask2Proposal10", "sounds/chapter1/automatedActions/task2/proposal10.mp3"
        ), (
            398, "yesSound", "sounds/chapter1/automatedActions/task2/yes.mp3"
        ), (
            399, "noSound", "sounds/chapter1/automatedActions/task2/no.mp3"
        );

    INSERT 
    INTO
        "sounds"
        ("id", "name", "path") 
    VALUES 
        (
            400, "detectSound", "sounds/chapter1/detect/entopismosstoixeiwn.mp3"
        ), (
            401, "chapter1DetectQuestion1", "sounds/chapter1/detect/question1.mp3"
        ), (
            402, "chapter1DetectQuestion2", "sounds/chapter1/detect/question2.mp3"
        ), (
            403, "chapter1DetectQuestion3", "sounds/chapter1/detect/question3.mp3"
        ), (
            404, "chapter1DetectQuestion4", "sounds/chapter1/detect/question4.mp3"
        ), (
            405, "katonomasiaDefinitionSound", "sounds/chapter2/orismoi.mp3"
        ), (
            406, "katonomasiaSoundsSound", "sounds/chapter2/hxoi.mp3"
        ), (
            407, "katonomasiaCompleteSentenceSound", "sounds/chapter2/simplirwsiProtasewn.mp3"
        ), (
            408, "chapter2Katonomasia", "sounds/chapter2/katonomasia.mp3"
        ), (
            409, "chapter2question1", "sounds/chapter2/question1.mp3"
        ), (
            410, "chapter2question2", "sounds/chapter2/question2.mp3"
        ), (
            411, "chapter2question3", "sounds/chapter2/question3.mp3"
        ), (
            412, "chapter2question4", "sounds/chapter2/question4.mp3"
        ), (
            413, "chapter2question5", "sounds/chapter2/question5.mp3"
        ), (
            414, "chapter2question6", "sounds/chapter2/question6.mp3"
        ), (
            415, "chapter2proposal1", "sounds/chapter2/proposal1.mp3"
        ), (
            416, "chapter2proposal2", "sounds/chapter2/proposal2.mp3"
        ), (
            417, "chapter2proposal3", "sounds/chapter2/proposal3.mp3"
        ), (
            418, "chapter2proposal4", "sounds/chapter2/proposal4.mp3"
        ), (
            419, "chapter2proposal5", "sounds/chapter2/proposal5.mp3"
        ), (
            420, "chapter2proposal6", "sounds/chapter2/proposal6.mp3"
        ), (
            421, "chapter2proposal7", "sounds/chapter2/proposal7.mp3"
        ), (
            422, "chapter2proposal8", "sounds/chapter2/proposal8.mp3"
        ), (
            423, "chapter2proposal9", "sounds/chapter2/proposal9.mp3"
        ), (
            424, "chapter2proposal10", "sounds/chapter2/proposal10.mp3"
        ), (
            425, "chapter2proposal11", "sounds/chapter2/proposal11.mp3"
        ), (
            426, "chapter2proposal12", "sounds/chapter2/proposal12.mp3"
        ), (
            427, "chapter2proposal13", "sounds/chapter2/proposal13.mp3"
        ), (
            428, "chapter2proposal14", "sounds/chapter2/proposal14.mp3"
        ), (
            429, "chapter2proposal15", "sounds/chapter2/proposal15.mp3"
        ), (
            430, "chapter2proposal16", "sounds/chapter2/proposal16.mp3"
        ), (
            431, "chapter2proposal17", "sounds/chapter2/proposal17.mp3"
        ), (
            432, "chapter2proposal18", "sounds/chapter2/proposal18.mp3"
        ), (
            433, "chapter2proposal19", "sounds/chapter2/proposal19.mp3"
        ), (
            434, "chapter2proposal20", "sounds/chapter2/proposal20.mp3"
        ), (
            435, "chapter2proposal21", "sounds/chapter2/proposal21.mp3"
        ), (
            436, "chapter2proposal22", "sounds/chapter2/proposal22.mp3"
        ), (
            437, "chapter2proposal23", "sounds/chapter2/proposal23.mp3"
        ), (
            438, "chapter2proposal24", "sounds/chapter2/proposal24.mp3"
        ), (
            439, "chapter2proposal25", "sounds/chapter2/proposal25.mp3"
        ), (
            440, "chapter2proposal26", "sounds/chapter2/proposal26.mp3"
        ), (
            441, "chapter2proposal27", "sounds/chapter2/proposal27.mp3"
        ), (
            442, "chapter2proposal28", "sounds/chapter2/proposal28.mp3"
        ), (
            443, "chapter2proposal29", "sounds/chapter2/proposal29.mp3"
        ), (
            444, "chapter2proposal30", "sounds/chapter2/proposal30.mp3"
        ), (
            445, "chapter2proposal31", "sounds/chapter2/proposal31.mp3"
        ), (
            446, "chapter2proposal32", "sounds/chapter2/proposal32.mp3"
        ), (
            447, "chapter2proposal33", "sounds/chapter2/proposal33.mp3"
        ), (
            448, "chapter2proposal34", "sounds/chapter2/proposal34.mp3"
        ), (
            449, "chapter2proposal35", "sounds/chapter2/proposal35.mp3"
        ), (
            450, "chapter2proposal36", "sounds/chapter2/proposal36.mp3"
        ), (
            451, "chapter2proposal37", "sounds/chapter2/proposal37.mp3"
        ), (
            452, "chapter2proposal38", "sounds/chapter2/proposal38.mp3"
        ), (
            453, "chapter2proposal39", "sounds/chapter2/proposal39.mp3"
        ), (
            454, "chapter2proposal40", "sounds/chapter2/proposal40.mp3"
        ), (
            455, "air", "sounds/chapter2/actions/air.mp3"
        ), (
            456, "bird", "sounds/chapter2/actions/bird.mp3"
        ), (
            457, "car", "sounds/chapter2/actions/car.mp3"
        ), (
            458, "cat", "sounds/chapter2/actions/cat.mp3"
        ), (
            459, "dog", "sounds/chapter2/actions/dog.mp3"
        ), (
            460, "dolphin", "sounds/chapter2/actions/dolphin.mp3"
        ), (
            461, "elephant", "sounds/chapter2/actions/elephant.mp3"
        ), (
            462, "fire", "sounds/chapter2/actions/fire.mp3"
        ), (
            463, "fly", "sounds/chapter2/actions/fly.mp3"
        ), (
            464, "lion", "sounds/chapter2/actions/lion.mp3"
        ), (
            465, "machine", "sounds/chapter2/actions/machine.mp3"
        ), (
            466, "monkey", "sounds/chapter2/actions/monkey.mp3"
        ), (
            467, "rain", "sounds/chapter2/actions/rain.mp3"
        ), (
            468, "sea", "sounds/chapter2/actions/sea.mp3"
        ), (
            469, "ship", "sounds/chapter2/actions/ship.mp3"
        ), (
            470, "traffic", "sounds/chapter2/actions/traffic.mp3"
        ), (
            471, "wolf", "sounds/chapter2/actions/wolf.mp3"
        ), (
            472, "combinationalSequenceSound", "sounds/chapter3/allilouxiaPraksewn.mp3"
        ), (
            473, "combinationalProfessionsSound", "sounds/chapter1/professions/taepaggelmata.mp3"
        ), (
            474, "combinationalObjectSound", "sounds/chapter1/objects/taantikeimena.mp3"
        ), (
            475, "combinationalActionSound", "sounds/chapter1/action/energies.mp3"
        ), (
            476, "chapter3Sisxetizomenesennoies", "sounds/chapter3/sisxetizomenesennoies.mp3"
        ), (
            477, "chapter3question1", "sounds/chapter3/question1.mp3"
        ), (
            478, "chapter3question2", "sounds/chapter3/question2.mp3"
        ), (
            479, "chapter3question3", "sounds/chapter3/question3.mp3"
        ), (
            480, "chapter3question4", "sounds/chapter3/question4.mp3"
        ), (
            481, "chapter3question5", "sounds/chapter3/question5.mp3"
        ), (
            482, "chapter3question6", "sounds/chapter3/question6.mp3"
        ), (
            483, "chapter3question7", "sounds/chapter3/question7.mp3"
        );
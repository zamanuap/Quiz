package com.example.quiz.dataModel

val jsonData = """
    [
        {
            "id" : 1,
            "text" : "Who is Canada’s Head of State?",
            "options" : [ "Prime Minister", "The Governor General", "The Monarch", "Supreme Court Chief Justice"],
            "correctAnswer" : 2
        },
        {
            "id" : 2,
            "text" : "What are the three levels of government in Canada?",
            "options" : [ "Federal, provincial/territorial, municipal", "Federal, state, local", "King, Parliament, Courts", "Senate, House, Cabinet"],
            "correctAnswer" : 0
        },
        {
            "id" : 3,
            "text" : "Which document outlines the rights and freedoms of Canadians?",
            "options" : [ "The British North America Act", "The Canadian Charter of Rights and Freedoms", "The Constitution Act of 1867", "The Citizenship Act"],
            "correctAnswer" : 1
        },
        {
            "id" : 4,
            "text" : "What is Canada’s national police force called?",
            "options" : [ "CSIS", "RCMP", "CBSA", "OPP"],
            "correctAnswer" : 1
        },
        {
            "id" : 5,
            "text" : "What are the two official languages of Canada?",
            "options" : [ "English and French", "English and Spanish", "French and German", "English and Inuktitut"],
            "correctAnswer" : 0
        }
    ]
""".trimIndent()
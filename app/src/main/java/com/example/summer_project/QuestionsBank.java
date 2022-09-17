package com.example.summer_project;

import java.util.ArrayList;

public class QuestionsBank {
    // les questions de level easy
    private static ArrayList<QuestionsList> level1Questions() {

        final ArrayList<QuestionsList> questionsLists = new ArrayList<>();

        //create objetcs of questionlists
        final QuestionsList question1 = new QuestionsList("Comment appelle-t-on le scientifique qui étudie les océans ?" ,"L’océanier","Le marin","L’océanographe","Le poissonnier","L’océanographe","");
        final QuestionsList question2 = new QuestionsList("Quel est le plus grand océan du monde ?","L’océan Arctique","L’océan Pacifique","L’océan Atlantique","L’océan Indien","L’océan Pacifique","");
        final QuestionsList question3 = new QuestionsList("Quelle est la plus grande créature marine ?","La baleine bleue","Le calamar géant","Le requin tigre","Le plancton","La baleine bleue","");
        final QuestionsList question4 = new QuestionsList("La Terre est recouverte par :","80% d'eau","72% d'eau","90% d'eau","68% d'eau","72% d'eau","");
        final QuestionsList question5 = new QuestionsList("Combien existe-t-il d'océans dans le monde ?","8","14","5","6","5","");
        final QuestionsList question6 = new QuestionsList("Laquelle de ces mers fait partie de l'Océan Pacifique ?","La mer de Corail","La mer Méditerranée","La mer Blanche","La mer du Nord","La mer de Corail","");
        // add questions to list
        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);
        questionsLists.add(question6);

        return questionsLists;

    }

// les questions de level medium
    private static ArrayList<QuestionsList> level2Questions() {

        final ArrayList<QuestionsList> questionsLists = new ArrayList<>();

        //create objetcs of questionlists
        final QuestionsList question1 = new QuestionsList("Comment appelle-t-on un groupe de poissons ?" ,"Un banc","Une meute","Un gang","Un group","Un banc","");
        final QuestionsList question2 = new QuestionsList("En quelle année l’océan Atlantique a-t-il été traversé en avion et sans escale pour la première fois ?","1899","1919","1886","1909","1919","");
        final QuestionsList question3 = new QuestionsList("Quel pays de l’océan Indien situé au ''raz de l’eau'' pourrait disparaître par la montée du niveau des eaux ?","L'Indonésie","L'Australie","Les Maldives","Madagascar","Les Maldives","");
        final QuestionsList question4 = new QuestionsList("Comment se déplace le son sous l’eau par rapport à l’air ?","Le son va 4.3 fois plus vite sous l'eau que dans l'air","Le son va 10 fois plus vite dans l'air que sous l'eau","Le son va 4.3 fois plus vite sous l'eau que dans l'air","Le son va 8.7 fois plus vite dans l'air que sous l'eau","Le son va 4.3 fois plus vite sous l'eau que dans l'air","");
        final QuestionsList question5 = new QuestionsList("Quel poisson fait partie du Top 3 des plus pêchés sur terre ?","Le colin d'Alaska","Le sardine","Le saumon","Le cabillaud d'Atlantique","Le colin d'Alaska","");
        final QuestionsList question6 = new QuestionsList("Quelle est la profondeur du point le plus profond des océans?","Près de 9km","Près de 12km","Près de 10km","Près de 11km","Près de 11km","");


        // add questions to list
        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);
        questionsLists.add(question6);

        return questionsLists;

    }
// les questions de level hard
    private static ArrayList<QuestionsList> level3Questions() {

        final ArrayList<QuestionsList> questionsLists = new ArrayList<>();

        //create objects of questionLists

        final QuestionsList question1 = new QuestionsList("Dans quel océan se situe le ''7ème continent'', une surface flottante recouverte d’au moins 1800 milliards de tonnes de plastique ?" ,"Océan Austral","Océan Pacifique","Océan Indien","Océan Atlantique","Océan Pacifique","");
        final QuestionsList question2 = new QuestionsList("Quelle est la créature marine la plus forte en camouflage ?" ,"La raie"," Le poisson pierre","La pieuvre mimétique","Le requin lutin","La pieuvre mimétique","");
        final QuestionsList question3 = new QuestionsList("À quoi sert le phytoplancton ?","A pecher les poissons","Il détruit le plastique","Il produit l'oxygène","Il réduit la température terrestre","Il détruit le plastique","");
        final QuestionsList question4 = new QuestionsList("Où se trouve le point le plus profond des océans ?","À la Fosse des Mariannes dans le Pacifique","Au milieu de l'Océan Indien","Au large du Cap Horn","Au milieu de l'Océan Atlantique","À la Fosse des Mariannes dans le Pacifique","");
        final QuestionsList question5 = new QuestionsList(" Parmi ces affirmations sur le crabe des cocotiers laquelle est fausse ?","Il peut grimper aux arbres","Il peut mesurer jusqu’à 1m d’une patte à l’autre","Il peut ouvrir des noix de coco avec ses pinces","Il peut rester 24h dans l’eau sans sortir respirer","Il peut ouvrir des noix de coco avec ses pinces","");
        final QuestionsList question6 = new QuestionsList("Lequel de ces lieux est une frontière entre l’océan Atlantique et l’océan Pacifique ?","Le Cap de Bonne-Espérance","Le Cap Horn","Le Golf du Mexique","Le Golfe de Saint-Laurent","Le Cap Horn","");

        // add questions to list
        questionsLists.add(question1);
        questionsLists.add(question2);
        questionsLists.add(question3);
        questionsLists.add(question4);
        questionsLists.add(question5);
        questionsLists.add(question6);

        return questionsLists;

    }
// la selection des questions selon le level selectionné
    public static ArrayList<QuestionsList> getQuestions(String selectedLevel) {
        switch (selectedLevel){
            case "level1":
                return level1Questions();
            case "level2":
                return level2Questions();
            default:
                return level3Questions();
        }
    }

}

package by.mvn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class Generate {
    public static Logger logger = LogManager.getLogger(Generate.class);
    protected static Random random = new Random();
    /*
        We use two strings: one with vowel letters, another with consonant letters.
        We duplicate some letters in those string, because they more popular in english words
     */
    static final String vowel ="eeeaaooiiu";
    static final String consonant ="ttnnsshrrddlcmwfgpbvkxjz";
    static final String[] firstNamesMale ={"Alexander", "Maxim", "Ivan", "Artem","Nikita","Dmitriy","Egor","Mihail","Andrew","Alexey","Vladimir","Denis", "Anton", "Vadim"};
    static final String[] firstNamesFemale ={"Anna","Mariya","Polina","Viktoriya","Ekaterina","Nataliya","Yuliya"};
    static final String[] secondNames ={"Smirnov", "Ivanov", "Sokolov", "Kozlov","Novikov","Morozov","Petrov","Volkov","Pavlov","Golubev","Medvedev","Jukov","Sidorov"};
    static final String[] cities ={"Minsk", "Vitebsk", "Gomel", "Mogilev","Brest","Grodno","Borisov","Jodino","Bobruysk","Minsk","Minsk"};
    static final String[] streets ={"Lenina", "Gagarina", "Masherova", "Pushkina","Mira","Kirova","Kalinina","Sadovaya","Chapaeva","Suvorova","Nezavisimosti"};
    static final String[] mailDomains ={"gmail.com", "mail.ru", "tut.by", "list.ru","live.com","yandex.ru"};

    static final char[] lowcaseLetters ="qwertyuiopasdfghjklzxcvbnm".toCharArray();
    static final char[] uppercaseLetters ="qwertyuiopasdfghjklzxcvbnm".toUpperCase().toCharArray();
    static final char[] specialCharacters="_-#$%&@".toCharArray();
    static boolean isMale = true;


    public static int intValue(int min, int max){
        return min+random.nextInt(max-min);
    }
    public static int intValue(){
        return intValue(0,255);
    }

    private static String randomStringFromArray(String[] array){
        int key = intValue(0, array.length-1);
        return array[key];
    }
    public static String randomFirstName(){
        isMale = random.nextBoolean();
        String firstName = isMale ? randomStringFromArray(firstNamesMale) : randomStringFromArray(firstNamesFemale);
        return firstName;
    }
    public static String randomSecondName(){
        String secondName = randomStringFromArray(secondNames);
        secondName = isMale ? secondName : secondName+"a";
        return secondName;
    }
    public static String randomCountry(){
        return "Belarus";
    }
    public static String randomCity(){
        return randomStringFromArray(cities);
    }
    public static String randomStreet() {
        return randomStringFromArray(streets);
    }
    public static String randomEmail(){
        StringBuilder email = new StringBuilder(readableWord(5,8));
        email.append(intValue(65,95));
        email.append('@');
        email.append(randomStringFromArray(mailDomains));
        return email.toString();
    }
    private static char randomVowel(){
        char[] vowels = vowel.toCharArray();
        int key = intValue(0,vowels.length);
        return vowels[key];
    }
    private static char randomConsonant(){
        char[] consonants = consonant.toCharArray();
        int key = intValue(0,consonants.length);
        return consonants[key];
    }
    public static String readableWord(){
        return readableWord((byte)4,(byte)10);
    }
    public static String readableWord(int from, int to){
        StringBuffer word=new StringBuffer("");
        boolean isNextVowel = random.nextBoolean();
        int wordLength = intValue(from,to);
        for (int i=0; i<wordLength; i++){
            if (isNextVowel){
                word.append(randomVowel());
            } else {
                word.append(randomConsonant());
            }
            isNextVowel=!isNextVowel;
        }
        return word.toString();
    }


}

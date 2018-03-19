package ru.liga;
import com.leff.midi.MidiFile;
import com.leff.midi.MidiTrack;
import com.leff.midi.event.MidiEvent;
import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;
import com.leff.midi.event.meta.Tempo;
import ru.liga.AnalysisMidi.*;
import ru.liga.HW.input_interface.InLoggers;
import ru.liga.HW.input_interface.TextAnalizeFile;
import ru.liga.HW.input_interface.TransMidi;
import ru.liga.songtask.content.Content;
import ru.liga.songtask.domain.Note;
import ru.liga.songtask.domain.SimpleMidiFile;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
///////lesson 4

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//////

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Всего нот: 15
 * <p>
 * Анализ диапазона:
 * верхняя: E4
 * нижняя: F3
 * диапазон: 11
 * <p>
 * Анализ длительности нот (мс):
 * 4285: 10
 * 2144: 5
 * <p>
 * Анализ нот по высоте:
 * E4: 3
 * D4: 3
 * A3: 3
 * G3: 3
 * F3: 3
 * <p>
 * Анализ интервалов:
 * 2: 9
 * 5: 3
 * 11: 2
 */
public class App {
    private static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        SimpleMidiFile simpleMidiFile = new SimpleMidiFile(Content.ZOMBIE);
<<<<<<< HEAD


    if (args.length==3)
    {
        if (args[0].equals("C:\\zombie.mid") && args[1].equals("analyze") && args[2].equals("-f"))
        {
            TextAnalizeFile textAnalizeFile = new TextAnalizeFile(simpleMidiFile);
            textAnalizeFile.save();
        }

    }
        if (args.length==2)
        {
            if (args[0].equals("C:\\zombie.mid") && args[1].equals("analyze") )
            {
                InLoggers inLoggers = new InLoggers(simpleMidiFile);
                inLoggers.getLoggers();
            }

        }

        if (args.length==6)
        {
            if (args[0].equals("C:\\zombie.mid") && args[1].equals("change") && args[2].equals("-trans") && args[3].equals("2") && args[4].equals("-tempo") && args[5].equals("20")  )
            {
                TransMidi transMidi = new TransMidi(simpleMidiFile);
                transMidi.get_Trans();
            }
        }





=======
       /////////////////////////////////////////////////////////////////////////////////////// ВСЕГО нот
        ArrayList<NoteSign> list_note= new ArrayList<>();
        for(int i=0;i<simpleMidiFile.vocalNoteList().size();i++)
        {

            if (!list_note.contains(simpleMidiFile.vocalNoteList().get(i).sign()))
                list_note.add(simpleMidiFile.vocalNoteList().get(i).sign());

        }
        System.out.println("Всего нот: " +list_note.size());
////////////////////////////////////////////////////////////////////////////////////Анализ диапозона
        int maxIndex = 0;
        int minIndex = 0;
        for(int i=0;i<list_note.size();i++)
        {
            if (list_note.get(i).higher(list_note.get(maxIndex))) {
                maxIndex = i;
            }
            if (list_note.get(i).lower(list_note.get(minIndex))) {
                minIndex = i;
            }

        }
           int diapason = list_note.get(maxIndex).getMidi()-list_note.get(minIndex).getMidi();
        System.out.println( "Анализ диапазона:\n" +
                 "верхняя: " + list_note.get(maxIndex).fullName() + "\n" +
                 "нижняя: " + list_note.get(minIndex).fullName()  + "\n" +
                 "диапазон: " + diapason);
/////////////////////////////////////////////////////////////////////////////////////////////Анализ интервалов:
        Map<Integer, Integer> hashMapInterval = new HashMap<>();

      int interval;
        for(int i=0;i<simpleMidiFile.vocalNoteList().size() - 1;i++)
        {
          interval = simpleMidiFile.vocalNoteList().get(i).sign().diffInSemitones(simpleMidiFile.vocalNoteList().get(i+1).sign());
            Integer frequency = hashMapInterval.get(interval);
            hashMapInterval.put(interval, frequency == null ? 1 : frequency + 1);
        }
        System.out.println("Анализ интервалов:");
        for (Map.Entry entry : hashMapInterval.entrySet()) {
            System.out.println( entry.getKey() + " : "
                    + entry.getValue());
        }
//////////////////////////////////////////////////////////////////////////////////////////Анализ нот по высоте:
        Map<String, Integer> hashMapNote = new HashMap<>();

        for(int i=0;i<simpleMidiFile.vocalNoteList().size();i++)
        {

            Integer count = hashMapNote.get(simpleMidiFile.vocalNoteList().get(i).sign().fullName());
            hashMapNote.put(simpleMidiFile.vocalNoteList().get(i).sign().fullName(), count== null ? 1 : count + 1);
        }

        System.out.println("Анализ нот по высоте:");
        for (Map.Entry entry : hashMapNote.entrySet()) {
            System.out.println( entry.getKey() + " : "
                    + entry.getValue());
        }
//////////////////////////////////////////////////////////////////////////////////////////Анализ длительности нот (мс):

        class note{
            Long time;
            int count;
            note(Long ThatTime,int ThatCount)
            {
               this.count = ThatCount;
               this.time = ThatTime;
            }
        }
        Map<String, note> hashMapNoteTime = new HashMap<>();

        for(int i=0;i<simpleMidiFile.vocalNoteList().size();i++)
        {

            note note = hashMapNoteTime.get(simpleMidiFile.vocalNoteList().get(i).sign().fullName());
            if(note==null)
            {
                note new_note= new note(simpleMidiFile.vocalNoteList().get(i).durationTicks(),1);
                hashMapNoteTime.put(simpleMidiFile.vocalNoteList().get(i).sign().fullName(),new_note );
            }else
            {
               int new_count=note.count + 1;
               Long new_time=note.time +  simpleMidiFile.vocalNoteList().get(i).durationTicks();
                note new_note= new note(new_time,new_count);
                hashMapNoteTime.put(simpleMidiFile.vocalNoteList().get(i).sign().fullName(),new_note );
            }
        }
        System.out.println("Анализ длительности нот (мс):");

        for (Map.Entry entry : hashMapNoteTime.entrySet()) {
            System.out.println(
                    hashMapNoteTime.get(entry.getKey()).time*simpleMidiFile.tickInMs() + " : " + hashMapNoteTime.get(entry.getKey()).count);

        }
>>>>>>> 65fb0d9eeadbd4ad1ea4db48010fdd94c0c65c0d

    }
}

package tk.bhavya.nouihabittracker;

import android.provider.BaseColumns;

public final class HabitContract {
    //An empty private constructor makes sure that the class is not going to be initialised.
        private HabitContract(){}

        public static final class HabitEntry implements BaseColumns {

            public final static String TABLE_NAME = "habits";
            public final static String COLUMN_NAME ="name";
            public final static String COLUMN_FEEL = "feel";
            public final static String COLUMN_FREQUENCY = "frequency";

    }
    }

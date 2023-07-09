package com.example.weatherapplication.di;

/*
@Module
@InstallIn(SingletonComponent.class)
public class DatastoreInjectorJava {
    private static final String USER_PREFERENCES = "user_preferences";
    @Singleton
    @Provides
    public DataStore<Preferences> providePreferencesDataStore(@ApplicationContext Context appContext ) {

        File dataStoreFile = new File(appContext.getFilesDir(), USER_PREFERENCES);

        return PreferenceDataStoreFactory.INSTANCE.create((Function0<? extends File>) dataStoreFile
        );

    }
}

 */
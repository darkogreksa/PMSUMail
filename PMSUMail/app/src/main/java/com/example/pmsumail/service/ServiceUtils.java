package com.example.pmsumail.service;

import android.graphics.Bitmap;

import com.example.pmsumail.model.Contact;
import com.example.pmsumail.util.ImageSerialization;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceUtils {

    public static final String SERVICE_API_PATH = "http://192.168.0.16:8080/api/";
    public static final String LOGIN = "accounts/{username}/{password}";
    public static final String USERNAME = "accounts/{username}";
    public static final String ACCOUNTS = "accounts/all";
    public static final String MESSAGES = "messages/all";
    public static final String MESSAGEID ="messages/{id}";
    public static final String MESSAGEADD = "messages/add";
    public static final String MESSAGEDELETE = "messages/delete/{id}";
    public static final String SORTMESSAGES = "messages/date";
    public static final String FOLDERS = "folders/all";
    public static final String FOLDERID = "folders/{id}";
    public static final String FOLDERADD = "folders/add";
    public static final String CONTACTS = "contacts/all";
    public static final String CONTACTID = "contacts/{id}";
    public static final String CONTACTADD = "contacts/add";
    public static final String TAGS = "tags/all";
    public static final String GETTAGBYMESSAGE = "tags/message/{id}";
    public static final String ADDTAGINMESSAGE = "messages/addTag/{messageId}/{tagId}";
    public static final String ATTACHMENTS = "attachments/all";
    public static final String ATTACHMENTID = "attachments/{id}";
    public static final String ATTACHMENTADD = "attachments/add";


    public static OkHttpClient test(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build();
        return client;
    }

    //static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:ss.SSSZ").create();

    static Gson gson = new GsonBuilder().registerTypeAdapter(Bitmap.class, ImageSerialization.getBitmapTypeAdapter())
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(SERVICE_API_PATH)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(test())
            .build();

    public static AccountService accountService = retrofit.create(AccountService.class);
    public static MessageService messageService = retrofit.create(MessageService.class);
    public static FolderService folderService = retrofit.create(FolderService.class);
    public static ContactService contactService = retrofit.create(ContactService.class);
    public static TagService tagService = retrofit.create(TagService.class);
    public static AttachmentService attachmentService = retrofit.create(AttachmentService.class);
}

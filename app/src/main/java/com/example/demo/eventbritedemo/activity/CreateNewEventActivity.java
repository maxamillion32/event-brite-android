package com.example.demo.eventbritedemo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.demo.eventbritedemo.R;
import com.example.demo.eventbritedemo.model.EventResponseModel;
import com.example.demo.eventbritedemo.model.ImageUploadModel;
import com.example.demo.eventbritedemo.utility.Constants;
import com.example.demo.eventbritedemo.utility.UriManager;
import com.example.demo.eventbritedemo.utility.Utility;
import com.example.demo.eventbritedemo.webservice.ApiCallMethods;
import com.example.demo.eventbritedemo.webservice.CustomCallback;
import com.example.demo.eventbritedemo.webservice.WebService;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.TimeZone;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class CreateNewEventActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 51;
    private static final int REQUEST_CODE_IMAGE = 61;
    private EditText eventName;
    private EditText eventCurrency;
    private Button btnCreateEvent;
    private String venueId;
    private Button venue;
    private Call<EventResponseModel.EventsEntity> createEventCall;
    private Button btnStartDate;
    private Button btnEndDate;
    private String startDate;
    private String endDate;
    private Call<ImageUploadModel> imageUploadCall;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_event);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();
    }

    private void initViews() {
        eventName = (EditText) findViewById(R.id.eventName);
        eventCurrency = (EditText) findViewById(R.id.eventCurrency);
        btnCreateEvent = (Button) findViewById(R.id.btnCreateEvent);
        venue = (Button) findViewById(R.id.venue);

        btnCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewEvent();
            }
        });

        venue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createVenue();
            }
        });

        btnStartDate = (Button) findViewById(R.id.btnStartDate);
        btnEndDate = (Button) findViewById(R.id.btnEndDate);

        final View.OnClickListener datePickerListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker((Button) v);
            }
        };

        btnStartDate.setOnClickListener(datePickerListener);
        btnEndDate.setOnClickListener(datePickerListener);

        final Button btnImagePick = (Button) findViewById(R.id.btnImagePick);
        btnImagePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askImageCapture();
//                initiateImageUpload();
            }
        });
    }

    private void initiateImageUpload(final String imagePath) {
        imageUploadCall = WebService.createServiceWithOauthHeader(ApiCallMethods.class)
                .getImageUpload("image-event-logo");

        imageUploadCall.enqueue(new CustomCallback<ImageUploadModel>() {
            @Override
            public void onSuccess(final Response<ImageUploadModel> response) {
                uploadFile(response.body(), imagePath);
            }

            @Override
            public boolean showLoader() {
                return true;
            }
        });
    }

    private void uploadFile(final ImageUploadModel imageUploadModel, String path) {
        // create upload service client
        final ApiCallMethods service = WebService
                .createRetrofitService(ApiCallMethods.class, ApiCallMethods.SERVICE_ENDPOINT);

        final File file = new File(path);

        // create RequestBody instance from file
        final RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        final MultipartBody.Part body =
                MultipartBody.Part.createFormData(imageUploadModel.getFile_parameter_name(),
                        file.getName(), requestFile);

        // add another part within the multipart request
        final String descriptionString = "hello, this is description speaking";
        final RequestBody description = RequestBody.create(
                MediaType.parse("multipart/form-data"), descriptionString
        );

        final ImageUploadModel.UploadDataEntity upload_data = imageUploadModel.getUpload_data();

        RequestBody fbody = RequestBody.create(MediaType.parse("image/*"), file);

        // finally, execute the request
        final Call<ResponseBody> call = service.uploadImage(
                getParams(upload_data),
                imageUploadModel.getUpload_url(),
                body
        );
        call.enqueue(new CustomCallback<ResponseBody>() {

            @Override
            public void onSuccess(Response<ResponseBody> response) {
                Log.v("Upload", "success");
                notifyImageUpload(imageUploadModel);
            }

            @Override
            public boolean showLoader() {
                return true;
            }
        });
    }

    private LinkedHashMap<String, String> getParams(ImageUploadModel.UploadDataEntity upload_data) {
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        params.put("key", upload_data.getKey());
        params.put("AWSAccessKeyId", upload_data.getAWSAccessKeyId());
        params.put("bucket", upload_data.getBucket());
        params.put("acl", upload_data.getAcl());
        params.put("signature", upload_data.getSignature());
        params.put("policy", upload_data.getPolicy());
        return params;
    }

    private void notifyImageUpload(ImageUploadModel imageUploadModel) {
        final Call<JsonObject> imageUploadModelCall = WebService
                .createServiceWithOauthHeader(ApiCallMethods.class)
                .notifyImageUpload(imageUploadModel.getUpload_token());

        imageUploadModelCall.enqueue(new CustomCallback<JsonObject>() {
            @Override
            public void onSuccess(Response<JsonObject> response) {

            }

            @Override
            public boolean showLoader() {
                return true;
            }
        });
    }

    private void askImageCapture() {
        new AlertDialog.Builder(CreateNewEventActivity.this)
                .setPositiveButton("Pick Gallery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openGallery();
                    }
                })
                .setNegativeButton("Capture new", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openCamera();
                    }
                })
                .create()
                .show();
    }

    private void openGallery() {
        final Intent intent = new Intent();
        if (Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT) {
            // For Android versions of KitKat or later, we use a
            // different intent to ensure
            // we can get the file path from the returned intent URI
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        } else {
            intent.setAction(Intent.ACTION_GET_CONTENT);
        }

        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_IMAGE);
    }

    private void openCamera() {
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final Uri fileUri = getOutputMediaFileUri(); // create a file to save the image
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name
        startActivityForResult(intent, REQUEST_CODE_IMAGE);
    }

    private Uri getOutputMediaFileUri() {
        return Uri.fromFile(Environment.getExternalStorageDirectory());
    }

    private void createVenue() {
        startActivityForResult(new Intent(this, CreateEventVenueActivity.class), REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (RESULT_OK == resultCode) {
            if (null != data) {
                if (REQUEST_CODE == requestCode) {
                    venueId = data.getStringExtra(Constants.IntentKeys.VENUE_ID);
                    return;
                }

                if (REQUEST_CODE_IMAGE == requestCode) {
                    displayImage(data);
                }
            }
        }
    }

    private void displayImage(Intent data) {
        final String imagePath = UriManager.getPath(data.getData(), this);
        Log.d(getLocalClassName(), "### " + imagePath);
        initiateImageUpload(imagePath);
    }

    private void createNewEvent() {

        createEventCall = WebService.createServiceWithOauthHeader(ApiCallMethods.class)
                .createNewEvent(getEventDetails());

        createEventCall
                .enqueue(new CustomCallback<EventResponseModel.EventsEntity>() {
                    @Override
                    public void onSuccess(Response<EventResponseModel.EventsEntity> response) {
                        Utility.showToast("Event Created Successfully");
                        gotoCreateEventTicketActivity(response.body());
                    }

                    @Override
                    public boolean showLoader() {
                        return true;
                    }
                });
    }

    private void gotoCreateEventTicketActivity(EventResponseModel.EventsEntity eventsEntity) {
        final Intent intent = new Intent(this, CreateEventTicketActivity.class);
        intent.putExtra(Constants.IntentKeys.EVENT, eventsEntity);
        startActivity(intent);
        finish();
    }

    private JsonObject getEventDetails() {

        final JsonObject start = new JsonObject();
        final TimeZone timeZone = TimeZone.getDefault();
        start.addProperty("timezone", timeZone.getID());
        start.addProperty("utc", startDate);

        final JsonObject end = new JsonObject();
        end.addProperty("timezone", timeZone.getID());
        end.addProperty("utc", endDate);

        final JsonObject name = new JsonObject();
        name.addProperty("html", eventName.getText().toString().trim());

        final JsonObject event = new JsonObject();
        event.add("start", start);
        event.add("end", end);
        event.add("name", name);
        event.addProperty("currency", eventCurrency.getText().toString().trim());
        if (!TextUtils.isEmpty(venueId)) {
            event.addProperty("venue_id", venueId);
        }

        final JsonObject model = new JsonObject();
        model.add("event", event);

        return model;
    }

    private void showDateTimePicker(final Button button) {
        final View dialogView = View.inflate(this, R.layout.date_time_picker, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        dialogView.findViewById(R.id.btnSetDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDateFromDatePickers(alertDialog, dialogView, button);
            }
        });
        alertDialog.setView(dialogView);
        alertDialog.show();
    }


    private void getDateFromDatePickers(AlertDialog alertDialog, View dialogView, Button button) {
        final DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.datePicker);
        final TimePicker timePicker = (TimePicker) dialogView.findViewById(R.id.timePicker);

        final Calendar calendar = Calendar.getInstance();
        calendar.set(datePicker.getYear(),
                datePicker.getMonth(),
                datePicker.getDayOfMonth(),
                timePicker.getCurrentHour(),
                timePicker.getCurrentMinute(), 0);
        alertDialog.dismiss();
        switch (button.getId()) {
            case R.id.btnStartDate:
                startDate = Utility.getFormattedDate(calendar);
                break;
            case R.id.btnEndDate:
                endDate = Utility.getFormattedDate(calendar);
                break;
        }
        button.setText(Utility.getStringDate(Utility.getFormattedDate(calendar)));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (null != createEventCall) {
            createEventCall.cancel();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.example.edwin.photoarchive.AzureClasses;

import android.os.AsyncTask;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageCredentials;
import com.microsoft.azure.storage.StorageCredentialsSharedAccessSignature;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

public abstract class AzureBlobLoader extends AsyncTask {
    //All instances will have the SAS token
    //The SAS token is valid for download/upload
    //It's valid (using KEY1) from 2-24-2017:12.00.00 AM to 2-24-2027:12.00.00AM just in case!!!
    // TODO SAS Key
    private final String sas = "?sv=2017-04-17&ss=bfqt&srt=sco&sp=rwdlacup&se=2018-11-10T03:26:58Z&st=2017-09-27T19:26:58Z&spr=https&sig=YKozpcGRTl3LZSMUJgBCmmmLNSteOSeAZPX8dNsARLA%3D";
    private StorageCredentials accountSAS;
    private CloudStorageAccount account;
    private CloudBlobClient blobClient;
    private CloudBlobContainer container;

    //DATABASE INFORMATION
    private MobileServiceClient DBClient;
    private MobileServiceTable<Image> imageTable;
    private MobileServiceTable<ICAV> icavTable;

    AzureBlobLoader(){
        try {
            //make credentials
            this.accountSAS = new StorageCredentialsSharedAccessSignature(sas);

            // Setup the cloud storage account.
            //Params: sas, use-http, url-endpoint, storage account name
            //TODO Blob Storage Account
            this.account = new CloudStorageAccount(accountSAS, true, null, "boephotostore");

            // Create a blob service client
            this.blobClient = account.createCloudBlobClient();

            // Get a reference to a container
            this.container = blobClient.getContainerReference("photocontainer");

        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public String getSas() {
        return sas;
    }

    public StorageCredentials getAccountSAS() {
        return accountSAS;
    }

    public CloudStorageAccount getAccount() {
        return account;
    }

    public CloudBlobClient getBlobClient() {
        return blobClient;
    }

    public CloudBlobContainer getContainer() {
        return container;
    }

    public MobileServiceClient getDBClient() {
        return DBClient;
    }

    public void setDBClient(MobileServiceClient DBClient) {
        this.DBClient = DBClient;
    }

    public MobileServiceTable<Image> getImageTable() {
        return imageTable;
    }

    public void setImageTable(MobileServiceTable<Image> imageTable) {
        this.imageTable = imageTable;
    }

    public MobileServiceTable<ICAV> getIcavTable() {
        return icavTable;
    }

    public void setIcavTable(MobileServiceTable<ICAV> icavTable) {
        this.icavTable = icavTable;
    }
}

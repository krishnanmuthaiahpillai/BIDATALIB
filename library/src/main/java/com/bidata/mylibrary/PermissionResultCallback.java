package com.bidata.mylibrary;

import java.util.ArrayList;

/**
 * Created by Dell-OP9010 on 1/4/2018.
 */

public interface PermissionResultCallback
{
    void PermissionGranted(int request_code);
    void PartialPermissionGranted(int request_code, ArrayList<String> granted_permissions);
    void PermissionDenied(int request_code);
    void NeverAskAgain(int request_code);
}

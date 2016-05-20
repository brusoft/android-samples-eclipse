// ©2016 Datalogic S.p.A. and/or its affiliates. All rights reserved.

package com.datalogic.examples.devicesampleapi;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.datalogic.device.info.SYSTEM;

/**
 * InfoActivity displays information about the device being used.
 */
public class InfoActivity extends Activity {

	// It will show device associated icon.
	private ImageButton btnBg;
	// Containing device infos.
	private TextView txtInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);

		txtInfo = (TextView) findViewById(R.id.txtInfo);
		btnBg = (ImageButton) findViewById(R.id.btnBg);
		
		getInfo();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reset, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if ( item.getItemId() == R.id.action_reset ) {
			getInfo();
			btnBg.setImageResource(R.drawable.ic_launcher);
			return true ;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Shows device information.
	 */
	public void getInfo() {
		txtInfo.setText(getDescription());
	}

	/**
	 * Activated by btnBg.  Displays DEVICE_IMAGE in btnBg.
	 */
	public void btnBgOnClick(View v) {
		Bitmap img = SYSTEM.DEVICE_IMAGE;
		btnBg.setImageBitmap(img);
	}

	public String getSDKVersion() {
		return SYSTEM.SDK_VERSION;
	}
	
	public String getServiceVersion() {
		return SYSTEM.SERVICES_VERSION;
	}

	public String getScannerType() {
		return SYSTEM.BARCODE_SCANNER_TYPE.toString();
	}

	public String getDescription() {
		String output = "Scanner Type: " + getScannerType() + "\n"
				+ "Boot Type: " + SYSTEM.BOOT_TYPE.toString() + "\n"
				+ "Device Model: " + android.os.Build.MODEL + "\n"
				+ "WiFi type: " + SYSTEM.WIFI_TYPE.toString() + "\n"
				+ "Firmware Version: " + SYSTEM.getVersions().get("FIRMWARE") + "\n"
				+ "Bootloader Version: " + SYSTEM.getVersions().get("BOOTLOADER") + "\n"
				+ "Datalogic SDK version: " + getSDKVersion() + "\n"
				+ "Datalogic Service version: " + getServiceVersion() + "\n";
		return output;
	}
}

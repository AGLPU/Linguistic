package com.example.sidemenu;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sidemenu.R;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

public class PaypalActivity extends Activity {

	// set to PaymentActivity.ENVIRONMENT_PRODUCTION to move real money.
	// set to PaymentActivity.ENVIRONMENT_SANDBOX to use your test credentials
	// from https://developer.paypal.com
	// set to PaymentActivity.ENVIRONMENT_NO_NETWORK to kick the tires without
	// communicating to PayPal's servers.
	ProductTable model1;
	databasehelper db;
	int counter;
	private static final String CONFIG_ENVIRONMENT = PaymentActivity.ENVIRONMENT_SANDBOX;

	// note that these credentials will differ between live & sandbox
	// environments.
	private static final String CONFIG_CLIENT_ID = "AQyQcRAsK_3PbGg4YaNNQn0gePz1oIUvfIESuupzEHJhm0aOMYD1awsMVmtQ";
	// when testing in sandbox, this is likely the -facilitator email address.
	private static final String CONFIG_RECEIVER_EMAIL = "dharmendrascpm-facilitator@gmail.com";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_paypal);

		Intent intent = new Intent(this, PayPalService.class);

		intent.putExtra(PaymentActivity.EXTRA_PAYPAL_ENVIRONMENT,
				CONFIG_ENVIRONMENT);
		intent.putExtra(PaymentActivity.EXTRA_CLIENT_ID, CONFIG_CLIENT_ID);
		intent.putExtra(PaymentActivity.EXTRA_RECEIVER_EMAIL,
				CONFIG_RECEIVER_EMAIL);

		startService(intent);
	}

	public void onBuyPressed(View pressed) {
		PayPalPayment thingToBuy = new PayPalPayment(new BigDecimal("1.75"),
				"USD", "hipster jeans");

		Intent intent = new Intent(this, PaymentActivity.class);
		intent.putExtra(PaymentActivity.EXTRA_PAYPAL_ENVIRONMENT,
				CONFIG_ENVIRONMENT);
		intent.putExtra(PaymentActivity.EXTRA_CLIENT_ID, CONFIG_CLIENT_ID);
		intent.putExtra(PaymentActivity.EXTRA_RECEIVER_EMAIL,
				CONFIG_RECEIVER_EMAIL);

		// It's important to repeat the clientId here so that the SDK has it if
		// Android restarts your
		// app midway through the payment UI flow.
		intent.putExtra(PaymentActivity.EXTRA_CLIENT_ID,
				"credential-from-developer.paypal.com");
		intent.putExtra(PaymentActivity.EXTRA_PAYER_ID,
				"your-customer-id-in-your-system");
		intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);

		startActivityForResult(intent, 0);
	}

	public int getItem() {
		Object tb = null;
		db = new databasehelper(getApplicationContext());
		db.getWritableDatabase();
		model1 = new ProductTable();

		db.getproducts();
		Log.d("amama", "amaj");
		// Stack st=new Stack();
		ArrayList al = new ArrayList();

		al = db.getproducts();
		int size = al.size();
		if (size == 0)
			tb = 0;
		else
			tb = al.get(size - 1);
		int tb1 = (Integer) tb;
		counter = tb1;
		Toast.makeText(getApplicationContext(), String.valueOf(counter),
				Toast.LENGTH_LONG).show();
		for (int i = 0; i < size; i++) {
			Log.d("aman", String.valueOf(al.get(i)));
		}
		Log.d("ckckc", String.valueOf(counter));

		return counter;

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			PaymentConfirmation confirm = data
					.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
			if (confirm != null) {
				try {
					Log.i("paymentExample", confirm.toJSONObject().toString(4));

					// TODO: send 'confirm' to your server for verification.
					// see
					// https://developer.paypal.com/webapps/developer/docs/integration/mobile/verify-mobile-payment/
					// for more details.
					counter = 20000;
					additem(counter);
				} catch (JSONException e) {
					Log.e("paymentExample",
							"an extremely unlikely failure occurred: ", e);
				}
			}
		} else if (resultCode == Activity.RESULT_CANCELED) {
			Log.i("paymentExample", "The user canceled.");
		} else if (resultCode == PaymentActivity.RESULT_PAYMENT_INVALID) {
			Log.i("paymentExample",
					"An invalid payment was submitted. Please see the docs.");
		}
	}

	public void additem(int counter) {
		// counter++;
		db = new databasehelper(getApplicationContext());
		db.getWritableDatabase();
		model1 = new ProductTable();
		model1.idno = counter;
		// model1.idno=24;
		db.addProduct(model1);
		// Log.d("id",model1.idno);
		// Toast.makeText(getApplicationContext(),
		// "Your New data has been saved.",
		// Toast.LENGTH_LONG).show();
	}

	@Override
	public void onDestroy() {
		stopService(new Intent(this, PayPalService.class));
		super.onDestroy();
	}
}

package cn.burgeon.core.ui;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import cn.burgeon.core.R;

public class LoginActivity extends BaseActivity {

	private String[] stores = { "门店1", "门店2", "门店3" };
	private Spinner storeSpinner;
	private Button configBtn;
	private Button loginBtn;
	private Button logoutBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setupFullscreen();
		setContentView(R.layout.activity_login);

		init();
	}

	private void init() {
		storeSpinner = (Spinner) findViewById(R.id.storeSpin);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				LoginActivity.this, android.R.layout.simple_spinner_item,
				stores);
		storeSpinner.setAdapter(adapter);

		configBtn = (Button) findViewById(R.id.configBtn);
		configBtn.setOnClickListener(new ClickEvent());
		loginBtn = (Button) findViewById(R.id.loginBtn);
		loginBtn.setOnClickListener(new ClickEvent());
		logoutBtn = (Button) findViewById(R.id.logoutBtn);
	}
	
/*	Response.Listener responseListener = new Response.Listener<String>() {
		@Override
		public void onResponse(String response) {
			//Log.d("zhang.h", response);
			returnResponse(response,btnID);
		}
	};*/

	class ClickEvent implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.configBtn:
				Map<String,String> params = new HashMap<String, String>();
				JSONArray array;
				JSONObject transactions;
				try {
					array = new JSONArray();
					transactions = new JSONObject();
					transactions.put("id", 112);
					transactions.put("command", "Query");
					
					JSONObject paramsInTransactions = new JSONObject();
					paramsInTransactions.put("table", 12899);
					paramsInTransactions.put("columns", new JSONArray().put("cardno").put("vipname"));
					transactions.put("params", paramsInTransactions);
					
					array.put(transactions);
					params.put("transactions", array.toString());
					sendRequest(params, new Response.Listener<String>() {
						@Override
						public void onResponse(String response) {
							//Log.d("zhang.h", response);
							
						}
					});
				} catch (JSONException e) {}
				break;
			case R.id.loginBtn:
				forwardActivity(SystemActivity.class);
				break;
			case R.id.logoutBtn:
				break;
			}
		}
	}

	
}

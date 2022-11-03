package org.example.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Currency implements Serializable {
	@SerializedName("Rate")
	private String rate;

	@SerializedName("Ccy")
	private String valuate;

	@SerializedName("id")
	private int id;

	@SerializedName("CcyNm_UZ")
	private String uz ;


	@SerializedName("Date")
	private String sana;

	@Override
	public String toString() {
		return "Currency  " +'\n'+
				" Rate : " + rate + '\n' +
				" Valuate : " + valuate + '\n' +
				" Id :" + id +'\n' +
				" UZ : " + uz + '\n' +
				" Date : " + sana + '\n';
	}
}
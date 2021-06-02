package br.com.zupacademy.brunoweberty.propostazup.seguranca;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.security.crypto.encrypt.Encryptors;

@Converter
public class EncryptToDatabase implements AttributeConverter<String, String>{

	@SuppressWarnings("deprecation")
	@Override
	public String convertToDatabaseColumn(String string) {
		try {
			return Encryptors.queryableText("${crypto}", "12345678").encrypt(string);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public String convertToEntityAttribute(String string) {
		try {
			return Encryptors.queryableText("${crypto}", "12345678").decrypt(string);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

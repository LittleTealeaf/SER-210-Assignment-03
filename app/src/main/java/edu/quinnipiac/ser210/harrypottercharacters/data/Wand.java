package edu.quinnipiac.ser210.harrypottercharacters.data;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * @author Thomas Kwashnak
 * Represents a wand. Even though I don't really use it in the app
 */
public class Wand implements Parcelable {

    private String wood;
    private String core;
    private double length;

    /**
     * Loads a Wand from a JsonObject
     * @param jsonObject
     * @throws JSONException
     */
    public Wand(JSONObject jsonObject) throws JSONException {
        wood = jsonObject.getString("wood");
        core = jsonObject.getString("core");
        try {
            length = jsonObject.getDouble("length");
        } catch(Exception e) {
            length = 0;
        }
    }

    /**
     * Loads a Wand from a Parcel
     * @param in
     */
    protected Wand(Parcel in) {
        wood = in.readString();
        core = in.readString();
        length = in.readDouble();
    }

    /**
     * Indicates the creating of Wands and Arrays
     */
    public static final Creator<Wand> CREATOR = new Creator<Wand>() {
        @Override
        public Wand createFromParcel(Parcel in) {
            return new Wand(in);
        }

        @Override
        public Wand[] newArray(int size) {
            return new Wand[size];
        }
    };

    public String getWood() {
        return wood;
    }

    public String getCore() {
        return core;
    }

    public double getLength() {
        return length;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Stores a wand to a parcel
     * @param parcel
     * @param i
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(wood);
        parcel.writeString(core);
        parcel.writeDouble(length);
    }

    /**
     * Returns if the wand actually exists
     * @return
     */
    public boolean exists() {
        return !(wood.equals("") || core.equals(""));
    }
}

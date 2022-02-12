/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ghp2021.ghp2021app.jsf.bean;

/**
 *
 * @author Ryota-Terai
 */
public class ShelterSearchResult {

    private final String administrativeAreaCode;
    private final String name;
    private final String address;
    private final double latitude;
    private final double longtitude;
    private final boolean p20007;
    private final boolean p20008;
    private final boolean p20009;
    private final boolean p20010;
    private final boolean p20011;
    private final boolean p20012;
    private final int numberOfEvacuus;
    private final int numberOfNonEvacuees;

    public ShelterSearchResult(String administrativeAreaCode, String name, String address, double latitude, double longtitude, boolean p20007, boolean p20008, boolean p20009, boolean p20010, boolean p20011, boolean p20012, int numberOfEvacuus, int numberOfNonEvacuees) {
        this.administrativeAreaCode = administrativeAreaCode;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.p20007 = p20007;
        this.p20008 = p20008;
        this.p20009 = p20009;
        this.p20010 = p20010;
        this.p20011 = p20011;
        this.p20012 = p20012;
        this.numberOfEvacuus = numberOfEvacuus;
        this.numberOfNonEvacuees = numberOfNonEvacuees;
    }

    public String getAdministrativeAreaCode() {
        return administrativeAreaCode;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public boolean isP20007() {
        return p20007;
    }

    public boolean isP20008() {
        return p20008;
    }

    public boolean isP20009() {
        return p20009;
    }

    public boolean isP20010() {
        return p20010;
    }

    public boolean isP20011() {
        return p20011;
    }

    public boolean isP20012() {
        return p20012;
    }

    public int getNumberOfEvacuus() {
        return numberOfEvacuus;
    }

    public int getNumberOfNonEvacuees() {
        return numberOfNonEvacuees;
    }

}

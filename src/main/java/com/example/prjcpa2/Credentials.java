package com.example.prjcpa2;

/** This is only used to edit credentials for each user type. Accounts are given to administrators by management (not handled by us).
 *
 */

public class Credentials {

    public static final String[][] GuestCredentials = {
            {"guest", "guest"}
    };

    public static final String[][] Credentials001 = {
            {"franz", "12345"},
            {"franz_galido@dlsu.edu.ph", "gorilladunk"}
    };

    public static final String[][] Credentials002 = {
            {"hanns", "13579"},
            {"hanns_angsanto@dlsu.edu.ph", "tensaisakuragi"}
    };

    public static final String[][] Credentials003 = {
            {"jarin", "02468"},
            {"gabriel_jarin@dlsu.edu.ph", "numberonef1fan"}
    };

    public static final String[][] Credentials004 = {
            {"aca", "36912"},
            {"elijah_aca@dlsu.edu.ph", "pausedgame2bhere"}
    };

    /** Method to check if a given username and password are valid and return the user type
     *
     */

    public static String getUserType(String username, String password) {
        for (String[] credential : Credentials001) {
            if (credential[0].equals(username) && credential[1].equals(password)) {
                return "Admin";
            }
        }
        for (String[] credential : Credentials002) {
            if (credential[0].equals(username) && credential[1].equals(password)) {
                return "Admin";
            }
        }
        for (String[] credential : Credentials003) {
            if (credential[0].equals(username) && credential[1].equals(password)) {
                return "Admin";
            }
        }
        for (String[] credential : Credentials004) {
            if (credential[0].equals(username) && credential[1].equals(password)) {
                return "Admin";
            }
        }
        for (String[] credential : GuestCredentials) {
            if (credential[0].equals(username) && credential[1].equals(password)) {
                return "Guest";
            }
        }
        return "INVALID";
    }
}

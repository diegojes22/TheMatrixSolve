package net.diego.sistemasdeecuaciones.more;

public class CoreList {
    public static String getConfigCoreList4Win(String args) {
        String problems[] = {
            "H2+O2=H2O",                          // 1 - a
            "N2+H2=NH3",                          // 2 - b
            "H2O+Na=NaOH+H2",                     // 3 - c
            "KClO3=KCl+O2",                       // 4 - d
            "BaO2 + HCl = BaCl2 + H2O2",          // 5 - e
            "H2SO4 + NaCl = Na2SO4 + HCl",        // 6 - f
            "FeS2 = Fe3S4 + S2",                  // 7 - g
            "H2SO4 + C = H2O + SO2 + CO2",        // 8 - h
            "SO2 + O2 = SO3",                     // 9 - i
            "HCl + MnO2 = MnCl2 + H2O + Cl2",     // 10 - j
            "K2CO3 + C = CO + K",                 // 11 - k
            "Ag2SO4 + NaCl = Na2SO4 + AgCl",      // 12 - l
            "NaNO3 + KCl = NaCl + KNO3",          // 13 - m
            "Fe2O3 + CO = CO2 + Fe",              // 14 - n
            "Na2CO3 + H2O + CO2 = NaHCO3",        // 15 - o
        };
        String solutions[] = {
            "2H2+O2=2H2O",                        // 1 - a
            "N2+3H2=2NH3",                        // 2 - b
            "2H2O+2Na=2NaOH+H2",                  // 3 - c
            "2KClO3=2KCl+3O2",                    // 4 - d
            "BaO2+2HCl=BaCl2+H2O2",               // 5 - e
            "H2SO4+2NaCl=Na2SO4+2HCl",            // 6 - f
            "3FeS2 = Fe3S4 + S2",                 // 7 - g
            "2H2SO4 + C = 2H2O + 2SO2 + CO2",     // 8 - h
            "2SO2 + O2 = 2SO3",                   // 9 - i
            "4HCl + MnO2 = MnCl2 + 2H2O + Cl2",   // 10 - j
            "K2CO3 + 2C = 3CO + 2K",              // 11 - k
            "Ag2SO4 + 2NaCl = Na2SO4 + 2AgCl",    // 12 - l
            "NaNO3 + KCl = NaCl + KNO3",          // 13 - m
            "Fe2O3 + 3CO = 3CO2 + 2Fe",           // 14 - n
            "Na2CO3 + H2O + CO2 = 2NaHCO3",       // 15 - o
        };

        args = args.replaceAll(" ", "");

        for(int i = 0; i < problems.length; i++) {
            problems[i] = problems[i].replaceAll(" ", "");
            solutions[i] = solutions[i].replaceAll(" ", "");

            if(problems[i].equalsIgnoreCase(args)) {
                return solutions[i];
            }
        }
        System.out.println("erro......");
        throw new ExceptionInInitializerError();

    }
}

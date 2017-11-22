package ca.ubc.cs.cpsc210.mindthegap.TfL;

import android.util.Log;
import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Wrapper for TfL Arrival Data Provider
 */
public class TfLHttpArrivalDataProvider extends AbstractHttpDataProvider {
    private Station stn;
    private static final String MDF_TAG = "MDF_TAG";

    public TfLHttpArrivalDataProvider(Station stn) {
        super();
        this.stn = stn;
    }

    @Override
    /**
     * Produces URL used to query TfL web service for expected arrivals at
     * station specified in call to constructor.
     *
     * @returns URL to query TfL web service for arrival data
     */
    protected URL getURL() throws MalformedURLException {
        //  /Line/{ids}/Arrivlas?stopPointId={stopPointId}
        // https://api.tfl.gov.uk/Line/district,central/Arrivals?stopPointId=940GZZLUNHG&app_id=&app_key=
        // https://api.tfl.gov.uk/Line/central/Arrivals?stopPointId=940GZZLUNHG&app_id=&app_key=
        // https://api.tfl.gov.uk/Line/centra/Arrivals?stopPointId=940GZZLUNHG&app_id=&app_key=
        String lines = "";
        for(Line l : stn.getLines()){
            lines =  l.getId() + ",";
        }
        lines = lines.substring(0,lines.length() - 1);
        String request = "https://api.tfl.gov.uk/Line/" + lines + "/Arrivals?stopPointId=" + stn.getID() + "&app_id=&app_key=";
        Log.i(MDF_TAG, request);

        // TODO Phase 2 Task 7

        return new URL(request);
    }
}

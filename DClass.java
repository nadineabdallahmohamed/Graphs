package graphs;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DClass {
public List<Dataset> getPassengersFromJsonFile() {
	        List<Dataset> allPassengers = new ArrayList<Dataset> ();
	        ObjectMapper objectMapper = new ObjectMapper ();
	        objectMapper.configure (DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	        try (InputStream input = new FileInputStream ("E:\\titanic_csv.json")) {
	            //Read
	            allPassengers = objectMapper.readValue (input, new TypeReference<List<Dataset>> () {
	            });
	        } catch (FileNotFoundException e) {
	            e.printStackTrace ();
	        } catch (IOException e) {
	            e.printStackTrace ();
	        }

return allPassengers;
}




public void graphPassengerClass(List<Dataset> passengerList) {
	Map<String, Long> result =
			passengerList.stream ().collect (
			Collectors.groupingBy (Dataset::getPclass, Collectors.counting () ) );

	
// Create Chart
PieChart chart = new PieChartBuilder ().width (800).height (600).title (getClass ().getSimpleName ()).build ();
// Customize Chart
Color[] sliceColors = new Color[]{new Color (180, 68, 50), new Color (130, 105, 120), new Color (80, 143, 160)};
chart.getStyler ().setSeriesColors (sliceColors);
// Series
chart.addSeries ("First Class", result.get ("1"));
chart.addSeries ("Second Class", result.get ("2"));
chart.addSeries ("Third Class", result.get ("3"));
// Show it
new SwingWrapper (chart).displayChart ();
}


public void graphsurvived(List<Dataset> passengerList) {
	
	Map<String, Long> result =
			passengerList.stream ().collect (
			Collectors.groupingBy (Dataset::getSurvived, Collectors.counting () ) );
	
	PieChart chart = new PieChartBuilder ().width (800).height (600).title (getClass ().getSimpleName ()).build ();
	// Customize Chart
	Color[] sliceColors = new Color[]{new Color (180, 68, 50), new Color (130, 105, 120)};
	chart.getStyler ().setSeriesColors (sliceColors);
	// Series
	chart.addSeries ("First Class", result.get ("0"));
	chart.addSeries ("Second Class", result.get ("1"));
	
	// Show it
	new SwingWrapper (chart).displayChart ();
}

public void graphgender(List<Dataset> passengerList) {
	
	Map<String, Long> result =
			passengerList.stream ().filter(p->p.getSurvived().equals("1")).collect (
			Collectors.groupingBy (Tiatainc::getSex, Collectors.counting () ) );
	
	
	PieChart chart = new PieChartBuilder ().width (800).height (600).title (getClass ().getSimpleName ()).build ();
	// Customize Chart
	Color[] sliceColors = new Color[]{new Color (180, 68, 50), new Color (130, 105, 120)};
	chart.getStyler ().setSeriesColors (sliceColors);
	// Series
	chart.addSeries ("First Class", result.get ("female"));
	chart.addSeries ("Second Class", result.get ("male"));
	
	// Show it
	new SwingWrapper (chart).displayChart ();
	
}

}

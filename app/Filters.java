/**
 * Created by dnduong on 12/20/2016.
 */
import play.mvc.EssentialFilter;
import play.filters.cors.CORSFilter;
import play.http.HttpFilters;
import javax.inject.Inject;

public class Filters implements HttpFilters {

    @Inject
    CORSFilter corsFilter;

    public EssentialFilter[] filters() {
        return new EssentialFilter[] { corsFilter.asJava() };
    }
}
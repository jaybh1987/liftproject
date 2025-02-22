package bootstrap.liftweb


import com.myco.model.BlogAPI
import net.liftweb.common._
import net.liftweb.http._
import net.liftweb.sitemap._


/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {
  def boot {
    // where to search snippet
    LiftRules.addToPackages("com.myco")
    LiftRules.dispatch.append(BlogAPI)
    LiftRules.supplementalHeaders.default.set(
     List(
       ("X-Lift-Version", LiftRules.liftVersion),
       ("Access-Control-Allow-Origin", "*"),
       ("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, HEAD, OPTIONS, PATCH"),
       ("Access-Control-Allow-Content-Type", "application/json"))
    )

    // Build SiteMap
    def sitemap = SiteMap()

    // set the sitemap.  Note if you don't want access control for
    // each page, just comment this line out.
    LiftRules.setSiteMapFunc(() => sitemap)

    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    //Lift CSP settings see http://content-security-policy.com/ and
    //Lift API for more information.
    LiftRules.securityRules = () => {
      SecurityRules(content = Some(ContentSecurityPolicy(
        scriptSources = List(
            ContentSourceRestriction.Self),
        styleSources = List(
            ContentSourceRestriction.Self)
            )))
    }
  }
}

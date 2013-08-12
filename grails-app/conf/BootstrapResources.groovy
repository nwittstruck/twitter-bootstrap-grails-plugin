def log = org.slf4j.LoggerFactory.getLogger('grails.plugins.twitterbootstrap.BootstrapResources')
def dev = grails.util.GrailsUtil.isDevelopmentEnv()

def applicationContext = org.codehaus.groovy.grails.commons.ApplicationHolder.application.mainContext
def lesscssPlugin = applicationContext.pluginManager.getGrailsPlugin('lesscss-resources') || applicationContext.pluginManager.getGrailsPlugin('less-resources')
def jqueryPlugin = applicationContext.pluginManager.getGrailsPlugin('jquery')
def configTagLib = org.codehaus.groovy.grails.commons.ApplicationHolder.application.config.grails.plugins.twitterbootstrap.fixtaglib
def configDefaultBundle = org.codehaus.groovy.grails.commons.ApplicationHolder.application.config.grails.plugins.twitterbootstrap.defaultBundle
if (!configDefaultBundle && !configDefaultBundle.equals(false)) {
    configDefaultBundle = 'bundle_bootstrap'
}

def dirLessSource
def dirTarget 

log.debug "dirLessSource: ${dirLessSource}"
log.debug "dirTarget: ${dirTarget}"

def cssFile = "bootstrap.css"
def cssminFile = "bootstrap.min.css"

log.debug "config: grails.plugins.twitterbootstrap.fixtaglib = ${configTagLib}"
log.debug "config: grails.plugins.twitterbootstrap.defaultBundle = ${configDefaultBundle}"

log.debug "is lesscss-resources plugin loaded? ${!!lesscssPlugin}"
log.debug "is jquery plugin loaded? ${!!jqueryPlugin}"

modules = {

    'bootstrap-fixtaglib' {
        defaultBundle 'fixtaglib'
        
        resource id: 'bootstrap-fixtaglib', url:[plugin: 'twitter-bootstrap', dir: 'css', file: 'bootstrap-fixtaglib.css'], disposition: 'head'
    }

    'bootstrap-css' {
        defaultBundle configDefaultBundle
        if (configTagLib) {
            dependsOn 'bootstrap-fixtaglib'
        }
        
        resource id: 'bootstrap-css', url:[plugin: 'twitter-bootstrap', dir: 'css', file: (dev ? cssFile : cssminFile)], disposition: 'head', exclude:'minify'
    }

    /**
    * Bootstrap JS resources
    */

    'bootstrap-alert' {
        defaultBundle configDefaultBundle
        if (jqueryPlugin) {
            dependsOn 'jquery'
        }

        resource id: 'bootstrap-alert', url:[plugin: 'twitter-bootstrap', dir: 'js', file: 'alert.js']
    }

    'bootstrap-button' {
        defaultBundle configDefaultBundle
        if (jqueryPlugin) {
            dependsOn "jquery"
        }
        
        resource id: 'bootstrap-button', url:[plugin: 'twitter-bootstrap', dir: 'js', file: 'button.js']
    }

    'bootstrap-carousel' {
        defaultBundle configDefaultBundle
        if (jqueryPlugin) {
            dependsOn "jquery"
        }

        resource id: 'bootstrap-carousel', url:[plugin: 'twitter-bootstrap', dir: 'js', file: 'carousel.js']
    }

    'bootstrap-collapse' {
        defaultBundle configDefaultBundle
        dependsOn 'bootstrap-transition'
        if (jqueryPlugin) {
            dependsOn "jquery"
        }

        resource id: 'bootstrap-collapse', url:[plugin: 'twitter-bootstrap', dir: 'js', file: 'collapse.js']
    }

    'bootstrap-dropdown' {
        defaultBundle configDefaultBundle
        if (jqueryPlugin) {
            dependsOn 'jquery'
        }

        resource id: 'bootstrap-dropdown', url:[plugin: 'twitter-bootstrap', dir: 'js', file: 'dropdown.js']
    }

    'bootstrap-modal' {
        defaultBundle configDefaultBundle
        if (jqueryPlugin) {
            dependsOn 'jquery'
        }
        
        resource id: 'bootstrap-modal', url:[plugin: 'twitter-bootstrap', dir: 'js', file: 'modal.js']
    }

    'bootstrap-popover' {
        defaultBundle configDefaultBundle
        dependsOn 'bootstrap-tooltip'
        
        if (jqueryPlugin) {
            dependsOn 'jquery'
        }

        resource id: 'bootstrap-popover', url:[plugin: 'twitter-bootstrap', dir: 'js', file: 'popover.js']
    }

    'bootstrap-scrollspy' {
        defaultBundle configDefaultBundle
        if (jqueryPlugin) {
            dependsOn 'jquery'
        }
        
        resource id: 'bootstrap-scrollspy', url:[plugin: 'twitter-bootstrap', dir: 'js', file: 'scrollspy.js']
    }

    'bootstrap-tab' {
        defaultBundle configDefaultBundle
        if (jqueryPlugin) {
            dependsOn "jquery"
        }
        
        resource id: 'bootstrap-tab', url:[plugin: 'twitter-bootstrap', dir: 'js', file: 'tab.js']
    }

    'bootstrap-tooltip' {
        defaultBundle configDefaultBundle
        if (jqueryPlugin) {
            dependsOn "jquery"
        }
        
        resource id: 'bootstrap-tooltip', url:[plugin: 'twitter-bootstrap', dir: 'js', file: 'tooltip.js']
    }

    'bootstrap-transition' {
        defaultBundle configDefaultBundle
        if (jqueryPlugin) {
            dependsOn "jquery"
        }
        
        resource id: 'bootstrap-transition', url:[plugin: 'twitter-bootstrap', dir: 'js', file: 'transition.js']
    }

    'bootstrap-js' {
        defaultBundle configDefaultBundle
        if (jqueryPlugin) {
            dependsOn 'jquery'
        }
        dependsOn 'bootstrap-alert,bootstrap-button,bootstrap-carousel,bootstrap-collapse,bootstrap-dropdown,bootstrap-modal,bootstrap-popover,bootstrap-scrollspy,bootstrap-tab,bootstrap-tooltip,bootstrap-transition'
    }

    'bootstrap-less' {
        defaultBundle configDefaultBundle
        if (configTagLib) {
            dependsOn 'bootstrap-fixtaglib'
        }
        
        resource id:'bootstrap-less', url:[plugin: 'twitter-bootstrap', dir: 'less', file: 'bootstrap.less'], attrs:[rel: "stylesheet/less", type:'css', order:100], disposition: 'head'
    }

    bootstrap {
        defaultBundle configDefaultBundle
        if (lesscssPlugin) {
            dependsOn 'bootstrap-less'
        } else {
            dependsOn 'bootstrap-css'
        }
        dependsOn 'bootstrap-js'
    }
       
}

/*
 * Configuration Files adapted from TechReborn by modmuss50 licensed as the MIT License (MIT)
 *
 * Please refer to https://github.com/TechReborn/RebornCore/blob/1.16/src/main/java/reborncore/common/config/Config.java
 *
 * Developer Note:  This appears to be the most comprehensive and flexible option to establish configuration files for a mod
 */

package nourl.mythicmetals.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IConfig
{
    /**
     * This the category of the config
     *
     * @return
     */
    String category() default "config";

    /**
     * This is the key for the config, the default is the field name.
     *
     * @return
     */
    String key() default "";

    /**
     * This is a comment that will be supplied along with the config, use this to explain what the config does
     *
     * @return
     */
    String comment() default "";

    /**
     * this is the config file name, the default is just config.cgf, use this is you wish to split the config into more than one file.
     *
     * @return
     */
    String config() default "config";
}
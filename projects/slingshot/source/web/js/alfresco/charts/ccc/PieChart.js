/**
 * Copyright (C) 2005-2013 Alfresco Software Limited.
 *
 * This file is part of Alfresco
 *
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * A PieChart
 *
 * @module alfresco/charts/ccc/PieChart
 * @extends alfresco/charts/ccc/Chart
 * @author Erik Winlöf
 */
define(["dojo/_base/declare",
   "alfresco/core/Core",
   "alfresco/core/I18nUtils",
   "alfresco/charts/ccc/Chart"],
      function(declare, AlfCore, I18nUtils, Chart) {

         var i18nScope = "alfresco.reports.PieChart";

         return declare([Chart], {

            pvcChartType: "PieChart",

            valuesMask: I18nUtils.msg(i18nScope, "label", "{category}", "{value}", "{value.percent}"),

            /**
             * An array of the i18n files to use with this widget.
             *
             * @instance
             * @type {object[]}
             * @default [{i18nFile: "./i18n/PieChart.properties"}]
             */
            i18nRequirements: [{i18nFile: "./i18n/PieChart.properties"}],

            createChartConfig: function alfresco_charts_ccc_PieChart__createChartConfig(){
               var config = this.inherited(arguments);

               // PieChart specific options
               if (this.valuesMask)
               {
                  config.valuesMask = this.valuesMask;
               }

               return config;
            }

         });
      });
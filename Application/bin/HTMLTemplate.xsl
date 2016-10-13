<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
   <xsl:template match="/">
      <html>
         <body>


         <div style="float:left;">
         
            <h1>Medals</h1>
            <table border="1">
               <tr>
                  <th>Country</th>
                  <th>Sport</th>
                  <th>Categorie</th>
                  <th>Medal</th>
                  <th>Winner</th>
               </tr>
               <xsl:for-each select="countrycolection/country">
                  <xsl:variable name="stuff" select="nation" />
                  <xsl:for-each select="medalcolection/medal">
                     <tr>
                        <td>
                          <xsl:value-of select="$stuff"/>
                          </td>
                        
                        <td>
                           <xsl:value-of select="sport" />
                        </td>
                        <td>
                           <xsl:value-of select="categorie" />
                        </td>
                        <td>
                           <xsl:value-of select="medal" />
                        </td>
                        <td>
                           <xsl:value-of select="winner" />
                        </td>

                      </tr>
                  </xsl:for-each>
               </xsl:for-each>
            </table>
         </div>

            
         <div style="float:right;">
            <h1>Medals</h1>
            <table border="1">
               <tr>
                  <th>Country</th>
                  <th>Gold</th>
                  <th>Silver</th>
                  <th>Bronze</th>
                  <th>Total</th>
               </tr>
               <xsl:for-each select="countrycolection/country">
                     <tr>
                     
                     <td>
                        <xsl:value-of select="nation" />
                     </td>
                     <td>
                        <xsl:value-of select="golds" />
                     </td>
                     <td>
                        <xsl:value-of select="silvers" />
                     </td>
                     <td>
                        <xsl:value-of select="bronzes" />
                     </td>
                     <td>
                        <xsl:value-of select="total" />
                     </td>

                      </tr>
               </xsl:for-each>
            </table>
         </div>
         
         </body>
      </html>
   </xsl:template>
</xsl:stylesheet>
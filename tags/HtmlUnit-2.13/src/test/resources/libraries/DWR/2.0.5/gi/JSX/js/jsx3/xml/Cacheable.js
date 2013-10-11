/*
 * Copyright (c) 2001-2007, TIBCO Software Inc.
 * Use, modification, and distribution subject to terms of license.
 */
jsx3.Class.defineInterface("jsx3.xml.Cacheable",null,function(k,n){var E=jsx3.xml.Document;var Ob=jsx3.xml.CDF;var bc=jsx3.util.Logger.getLogger(k.jsxclass.getName());k.DEFAULTSTYLESHEET=jsx3.resolveURI("jsx:///xsl/xml.xsl");k.DEFAULTXSLCACHEID="JSX_XML_XSL";k.CLEANUPRESOURCES=0;k.SHARERESOURCES=1;n.doTransform=function(m,i){var gb=this.getNodeSet()!=null?this.getNodeSet():this.getXML();if(m==null&&this.jsxxslparams!=null)m=this.jsxxslparams;var Eb=0;var sb="";if(gb!=null){Eb=1;var cc=this.getXSL();if(cc!=null){Eb=2;sb=gb.transformNode(cc,m);if(sb!="")Eb=3;}}if(Eb==2){}else{if(Eb==1){if(i!=true)bc.error("XML02","Transformation Failed: When attempting to load the XSL for the control, "+this.getId()+", it resulted in a null object.",3);}else{if(Eb==0){}}}return sb;};n.getXSLParams=function(){if(this.jsxxslparams==null)this.jsxxslparams={};return this.jsxxslparams;};n.setXSLParam=function(p,r){if(r!=null)this.getXSLParams()[p]=r;else delete this.getXSLParams()[p];return this;};n.removeXSLParam=function(o){delete this.getXSLParams()[o];return this;};n.removeXSLParams=function(){delete this.jsxxslparams;return this;};n.getNodeSet=function(){return this._jsxft;};n.setNodeSet=function(f){this._jsxft=f;};n.onDestroyCached=function(q){if(this.getShareResources()==k.CLEANUPRESOURCES)this.resetCacheData(q.getServer());delete this._jsxft;};n.resetCacheData=function(a){var oc=(a||this.getServer()).getCache();oc.clearById(this.getXSLId());oc.clearById(this.getXMLId());};n.resetXmlCacheData=function(e){var lc=(e||this.getServer()).getCache();lc.clearById(this.getXMLId());};n.resetXslCacheData=function(b){var Lb=(b||this.getServer()).getCache();Lb.clearById(this.getXSLId());};n.clearXmlData=function(){this.getServer().getCache().setDocument(this.getXMLId(),Ob.newDocument());};n.getShareResources=function(){return this.jsxshare==null?k.CLEANUPRESOURCES:this.jsxshare;};n.setShareResources=function(h){this.jsxshare=h;return this;};n.getXML=function(){var lc=this.getServer();if(lc==null)return Ob.newDocument();var bb=lc.getCache().getDocument(this.getXMLId());if(bb==null){var eb=this.getXMLString();if(!jsx3.util.strEmpty(eb)){bb=new E();bb.loadXML(eb);}else{var Wb=this.getXMLURL();if(!jsx3.util.strEmpty(Wb)){bb=new E();bb.load(this.getUriResolver().resolveURI(Wb));}else{bb=Ob.newDocument();}}if(bb.hasError()){bc.error("Error parsing or loading the XML file for "+this+": "+bb.getError().description);return bb;}bb=this.setSourceXML(bb);}return bb;};n.setSourceXML=function(a){a=this.Tt(a);this.getServer().getCache().setDocument(this.getXMLId(),a);if(this.instanceOf(Ob)){if(a.getNodeName()=="data"&&a.getAttribute("jsxassignids")=="1")this.assignIds();this.convertProperties(this.getServer().getProperties());}return a;};n.getXMLId=function(){return this.jsxxmlid||this.getId()+"_XML";};n.setXMLId=function(s){this.jsxxmlid=s;return this;};n.getXMLString=function(){return this.jsxxml;};n.setXMLString=function(b){this.jsxxml=b;return this;};n.getXMLURL=function(){return this.jsxxmlurl;};n.setXMLURL=function(b){this.jsxxmlurl=b;return this;};n.getXSL=function(m){var hb=this.getXSLId();var Bc=this.getServer().getCache().getDocument(hb);if(Bc==null){Bc=new E();if(this.getXSLString()!=null){Bc.loadXML(this.getXSLString());}else{if(this.getXSLURL()!=null){Bc.load(this.getUriResolver().resolveURI(this.getXSLURL()));}else{if(m!=true){Bc.load(k.DEFAULTSTYLESHEET);hb=k.DEFAULTXSLCACHEID;}else{return null;}}}if(Bc.hasError()){bc.error("Error parsing or loading the XSL file for "+this+": "+Bc.getError().description);return Bc;}this.getServer().getCache().setDocument(hb,Bc);}return Bc;};n.getXSLId=function(){return this.jsxxslid||this.getId()+"_XSL";};n.setXSLId=function(a){this.jsxxslid=a;return this;};n.getXSLString=function(){return this.jsxxsl;};n.setXSLString=function(c){this.jsxxsl=c;return this;};n.getXSLURL=function(){return this.jsxxslurl;};n.setXSLURL=function(p){this.jsxxslurl=p;return this;};n.getXMLTransformers=function(){return this.jsxxmltrans!=null?this.jsxxmltrans.split(/\s*,\s*/g):[];};n.setXMLTransformers=function(a){this.jsxxmltrans=a!=null?a instanceof Array?a.join(","):a:null;};n.Tt=function(d){var Y=this.getXMLTransformers();if(Y.length>0){var v=this.getServer();var nc=v.getCache();var ib=d;for(var wb=0;wb<Y.length;wb++){var Q=Y[wb];var Fb=nc.getDocument(Q);if(Fb==null){Q=v.resolveURI(Q);Fb=nc.openDocument(Q,Q);}if(Fb==null||Fb.hasError()){bc.warn("Bad XML transformer for "+this+": "+Q+" ("+Fb.getError().description+")");nc.clearById(Q);continue;}var ub=new jsx3.xml.Processor(ib,Fb,this.getXSLParams());ib=ub.transformToObject();if(ib.hasError()){throw new jsx3.Exception("Error transforming XML for object "+this+": "+ib.getError().description);}}return ib;}else{return d;}};k.getVersion=function(){return "3.00.00";};});jsx3.xml.Cacheable.prototype.resetData=jsx3.xml.Cacheable.prototype.clearXmlData;

(function(){var $wnd = window;var $doc = $wnd.document;var $moduleName, $moduleBase;var _,lL='com.google.gwt.core.client.',mL='com.google.gwt.lang.',nL='com.google.gwt.sample.mail.client.',oL='com.google.gwt.user.client.',pL='com.google.gwt.user.client.impl.',qL='com.google.gwt.user.client.ui.',rL='com.google.gwt.user.client.ui.impl.',sL='java.lang.',tL='java.util.';function kL(){}
function uF(a){return this===a;}
function vF(){return mG(this);}
function sF(){}
_=sF.prototype={};_.eQ=uF;_.hC=vF;_.tN=sL+'Object';_.tI=1;function u(){return C();}
function v(){return D();}
function w(a){return a==null?null:a.tN;}
var x=null;function A(a){return a==null?0:a.$H?a.$H:(a.$H=E());}
function B(a){return a==null?0:a.$H?a.$H:(a.$H=E());}
function C(){var b=$doc.location.href;var a=b.indexOf('#');if(a!= -1)b=b.substring(0,a);a=b.indexOf('?');if(a!= -1)b=b.substring(0,a);a=b.lastIndexOf('/');if(a!= -1)b=b.substring(0,a);return b.length>0?b+'/':'';}
function D(){return $moduleBase;}
function E(){return ++F;}
var F=0;function cb(b,a){if(!Ab(a,2)){return false;}return gb(b,zb(a,2));}
function db(a){return A(a);}
function eb(){return [];}
function fb(){return {};}
function hb(a){return cb(this,a);}
function gb(a,b){return a===b;}
function ib(){return db(this);}
function ab(){}
_=ab.prototype=new sF();_.eQ=hb;_.hC=ib;_.tN=lL+'JavaScriptObject';_.tI=7;function kb(c,a,d,b,e){c.a=a;c.b=b;c.tN=e;c.tI=d;return c;}
function mb(a,b,c){return a[b]=c;}
function ob(a,b){return nb(a,b);}
function nb(a,b){return kb(new jb(),b,a.tI,a.b,a.tN);}
function pb(b,a){return b[a];}
function rb(b,a){return b[a];}
function qb(a){return a.length;}
function tb(e,d,c,b,a){return sb(e,d,c,b,0,qb(b),a);}
function sb(j,i,g,c,e,a,b){var d,f,h;if((f=pb(c,e))<0){throw new dF();}h=kb(new jb(),f,pb(i,e),pb(g,e),j);++e;if(e<a){j=cG(j,1);for(d=0;d<f;++d){mb(h,d,sb(j,i,g,c,e,a,b));}}else{for(d=0;d<f;++d){mb(h,d,b);}}return h;}
function ub(f,e,c,g){var a,b,d;b=qb(g);d=kb(new jb(),b,e,c,f);for(a=0;a<b;++a){mb(d,a,rb(g,a));}return d;}
function vb(a,b,c){if(c!==null&&a.b!=0&& !Ab(c,a.b)){throw new dE();}return mb(a,b,c);}
function jb(){}
_=jb.prototype=new sF();_.tN=mL+'Array';_.tI=8;function yb(b,a){return !(!(b&&Fb[b][a]));}
function zb(b,a){if(b!=null)yb(b.tI,a)||Eb();return b;}
function Ab(b,a){return b!=null&&yb(b.tI,a);}
function Bb(a){return a&65535;}
function Cb(a){return ~(~a);}
function Db(a){if(a>(AE(),BE))return AE(),BE;if(a<(AE(),CE))return AE(),CE;return a>=0?Math.floor(a):Math.ceil(a);}
function Eb(){throw new jE();}
function ac(b,d){_=d.prototype;if(b&& !(b.tI>=_.tI)){var c=b.toString;for(var a in _){b[a]=_[a];}b.toString=c;}return b;}
var Fb;function uA(b,a){fB(b.F(),a,true);}
function wA(a){return ai(a.D());}
function xA(a){return bi(a.D());}
function yA(a){return hi(a.r,'offsetHeight');}
function zA(a){return hi(a.r,'offsetWidth');}
function AA(d,b,a){var c=b.parentNode;if(!c){return;}c.insertBefore(a,b);c.removeChild(b);}
function BA(b,a){if(b.r!==null){AA(b,b.r,a);}b.r=a;}
function CA(b,c,a){b.ec(c);b.ac(a);}
function DA(b,a){eB(b.F(),a);}
function EA(b,a){aj(b.D(),a|ji(b.D()));}
function FA(){return this.r;}
function aB(){return this.r;}
function bB(a){return ii(a,'className');}
function cB(a){BA(this,a);}
function dB(a){Fi(this.r,'height',a);}
function eB(a,b){zi(a,'className',b);}
function fB(c,j,a){var b,d,e,f,g,h,i;if(c===null){throw xF(new wF(),'Null widget handle. If you are creating a composite, ensure that initWidget() has been called.');}j=eG(j);if(aG(j)==0){throw rE(new qE(),'Style names cannot be empty');}i=bB(c);e=EF(i,j);while(e!=(-1)){if(e==0||AF(i,e-1)==32){f=e+aG(j);g=aG(i);if(f==g||f<g&&AF(i,f)==32){break;}}e=FF(i,j,e+1);}if(a){if(e==(-1)){if(aG(i)>0){i+=' ';}zi(c,'className',i+j);}}else{if(e!=(-1)){b=eG(dG(i,0,e));d=eG(cG(i,e+aG(j)));if(aG(b)==0){h=d;}else if(aG(d)==0){h=b;}else{h=b+' '+d;}zi(c,'className',h);}}}
function gB(a,b){a.style.display=b?'':'none';}
function hB(a){gB(this.r,a);}
function iB(a){Fi(this.r,'width',a);}
function tA(){}
_=tA.prototype=new sF();_.D=FA;_.F=aB;_.Eb=cB;_.ac=dB;_.cc=hB;_.ec=iB;_.tN=qL+'UIObject';_.tI=11;_.r=null;function qC(a){if(!a.eb()){throw uE(new tE(),"Should only call onDetach when the widget is attached to the browser's document");}try{a.vb();}finally{a.y();Ai(a.D(),null);a.o=false;}}
function rC(a){if(Ab(a.q,17)){zb(a.q,17).Bb(a);}else if(a.q!==null){throw uE(new tE(),"This widget's parent does not implement HasWidgets");}}
function sC(b,a){if(b.eb()){Ai(b.D(),null);}BA(b,a);if(b.eb()){Ai(a,b);}}
function tC(b,a){b.p=a;}
function uC(c,b){var a;a=c.q;if(b===null){if(a!==null&&a.eb()){c.mb();}c.q=null;}else{if(a!==null){throw uE(new tE(),'Cannot set a new parent without first clearing the old parent');}c.q=b;if(b.eb()){c.ib();}}}
function vC(){}
function wC(){}
function xC(){return this.o;}
function yC(){if(this.eb()){throw uE(new tE(),"Should only call onAttach when the widget is detached from the browser's document");}this.o=true;Ai(this.D(),this);this.x();this.pb();}
function zC(a){}
function AC(){qC(this);}
function BC(){}
function CC(){}
function DC(a){sC(this,a);}
function rB(){}
_=rB.prototype=new tA();_.x=vC;_.y=wC;_.eb=xC;_.ib=yC;_.jb=zC;_.mb=AC;_.pb=BC;_.vb=CC;_.Eb=DC;_.tN=qL+'Widget';_.tI=12;_.o=false;_.p=null;_.q=null;function Av(b,a){uC(a,b);}
function Cv(b,a){uC(a,null);}
function Dv(){var a,b;for(b=this.fb();b.db();){a=zb(b.hb(),12);a.ib();}}
function Ev(){var a,b;for(b=this.fb();b.db();){a=zb(b.hb(),12);a.mb();}}
function Fv(){}
function aw(){}
function zv(){}
_=zv.prototype=new rB();_.x=Dv;_.y=Ev;_.pb=Fv;_.vb=aw;_.tN=qL+'Panel';_.tI=13;function wx(a){xx(a,fh());return a;}
function xx(b,a){b.Eb(a);return b;}
function yx(a,b){if(a.n!==null){throw uE(new tE(),'SimplePanel can only contain one child widget');}a.dc(b);}
function Ax(a,b){if(b===a.n){return;}if(b!==null){rC(b);}if(a.n!==null){a.Bb(a.n);}a.n=b;if(b!==null){ch(a.C(),a.n.D());Av(a,b);}}
function Bx(){return this.D();}
function Cx(){return sx(new qx(),this);}
function Dx(a){if(this.n!==a){return false;}Cv(this,a);ri(this.C(),a.D());this.n=null;return true;}
function Ex(a){Ax(this,a);}
function px(){}
_=px.prototype=new zv();_.C=Bx;_.fb=Cx;_.Bb=Dx;_.dc=Ex;_.tN=qL+'SimplePanel';_.tI=14;_.n=null;function hw(){hw=kL;vw=new BD();}
function cw(a){hw();xx(a,bE(vw));ow(a,0,0);return a;}
function dw(b,a){hw();cw(b);b.g=a;return b;}
function ew(c,a,b){hw();dw(c,a);c.k=b;return c;}
function fw(b,a){if(a.blur){a.blur();}}
function gw(c){var a,b,d;a=c.l;if(!a){pw(c,false);sw(c);}b=Db((pk()-jw(c))/2);d=Db((ok()-iw(c))/2);ow(c,qk()+b,rk()+d);if(!a){pw(c,true);}}
function iw(a){return yA(a);}
function jw(a){return zA(a);}
function kw(a){lw(a,false);}
function lw(b,a){if(!b.l){return;}b.l=false;Fm(fx(),b);DD(vw,b.D());}
function mw(a){var b;b=a.n;if(b!==null){if(a.h!==null){b.ac(a.h);}if(a.i!==null){b.ec(a.i);}}}
function nw(e,b){var a,c,d,f;d=Ch(b);c=oi(e.D(),d);f=Eh(b);switch(f){case 128:{a=e.ob(Bb(zh(b)),fv(b));return a&&(c|| !e.k);}case 512:{a=(Bb(zh(b)),fv(b),true);return a&&(c|| !e.k);}case 256:{a=(Bb(zh(b)),fv(b),true);return a&&(c|| !e.k);}case 4:case 8:case 64:case 1:case 2:{if((ah(),ti)!==null){return true;}if(!c&&e.g&&f==4){lw(e,true);return true;}break;}case 2048:{if(e.k&& !c&&d!==null){fw(e,d);return false;}}}return !e.k||c;}
function ow(c,b,d){var a;if(b<0){b=0;}if(d<0){d=0;}c.j=b;c.m=d;a=c.D();Fi(a,'left',b+'px');Fi(a,'top',d+'px');}
function pw(a,b){Fi(a.D(),'visibility',b?'visible':'hidden');FD(vw,a.D(),b);}
function qw(a,b){Ax(a,b);mw(a);}
function rw(a,b){a.i=b;mw(a);if(aG(b)==0){a.i=null;}}
function sw(a){if(a.l){return;}a.l=true;bh(a);Fi(a.D(),'position','absolute');if(a.m!=(-1)){ow(a,a.j,a.m);}Dm(fx(),a);ED(vw,a.D());}
function tw(){return this.D();}
function uw(){return this.D();}
function ww(){si(this);qC(this);}
function xw(a){return nw(this,a);}
function yw(a,b){return true;}
function zw(a){this.h=a;mw(this);if(aG(a)==0){this.h=null;}}
function Aw(a){pw(this,a);}
function Bw(a){qw(this,a);}
function Cw(a){rw(this,a);}
function bw(){}
_=bw.prototype=new px();_.C=tw;_.F=uw;_.mb=ww;_.nb=xw;_.ob=yw;_.ac=zw;_.cc=Aw;_.dc=Bw;_.ec=Cw;_.tN=qL+'PopupPanel';_.tI=15;_.g=false;_.h=null;_.i=null;_.j=(-1);_.k=false;_.l=false;_.m=(-1);var vw;function dp(){dp=kL;hw();}
function Fo(a){a.a=kt(new Eq());a.f=mq(new hq());}
function ap(a){dp();bp(a,false);return a;}
function bp(b,a){dp();cp(b,a,true);return b;}
function cp(c,a,b){dp();ew(c,a,b);Fo(c);gt(c.f,0,0,c.a);c.f.ac('100%');Es(c.f,0);at(c.f,0);bt(c.f,0);qr(c.f.b,1,0,'100%');tr(c.f.b,1,0,'100%');pr(c.f.b,1,0,(vt(),wt),(Et(),Ft));qw(c,c.f);DA(c,'gwt-DialogBox');DA(c.a,'Caption');kv(c.a,c);return c;}
function ep(b,a){mv(b.a,a);}
function fp(a,b){if(a.b!==null){Ds(a.f,a.b);}if(b!==null){gt(a.f,1,0,b);}a.b=b;}
function gp(a){if(Eh(a)==4){if(oi(this.a.D(),Ch(a))){Fh(a);}}return nw(this,a);}
function hp(a,b,c){this.e=true;wi(this.a.D());this.c=b;this.d=c;}
function ip(a){}
function jp(a){}
function kp(c,d,e){var a,b;if(this.e){a=d+wA(this);b=e+xA(this);ow(this,a-this.c,b-this.d);}}
function lp(a,b,c){this.e=false;qi(this.a.D());}
function mp(a){if(this.b!==a){return false;}Ds(this.f,a);return true;}
function np(a){fp(this,a);}
function op(a){rw(this,a);this.f.ec('100%');}
function Eo(){}
_=Eo.prototype=new bw();_.nb=gp;_.qb=hp;_.rb=ip;_.sb=jp;_.tb=kp;_.ub=lp;_.Bb=mp;_.dc=np;_.ec=op;_.tN=qL+'DialogBox';_.tI=16;_.b=null;_.c=0;_.d=0;_.e=false;function jc(){jc=kL;dp();}
function ic(c){var a,b;jc();ap(c);ep(c,'About the Mail Sample');a=lB(new jB());b=lt(new Eq(),"This sample application demonstrates the construction of a complex user interface using GWT's built-in widgets.  Have a look at the code to see how easy it is to build your own apps!");DA(b,'mail-AboutText');mB(a,b);mB(a,mn(new en(),'Close',fc(new ec(),c)));fp(c,a);return c;}
function kc(a,b){switch(a){case 13:case 27:kw(this);break;}return true;}
function dc(){}
_=dc.prototype=new Eo();_.ob=kc;_.tN=nL+'AboutDialog';_.tI=17;function fc(b,a){b.a=a;return b;}
function hc(a){kw(this.a);}
function ec(){}
_=ec.prototype=new sF();_.lb=hc;_.tN=nL+'AboutDialog$1';_.tI=18;function yo(a){if(a.h===null){throw uE(new tE(),'initWidget() was never called in '+w(a));}return a.r;}
function zo(a,b){if(a.h!==null){throw uE(new tE(),'Composite.initWidget() may only be called once.');}rC(b);a.Eb(b.D());a.h=b;uC(b,a);}
function Ao(){return yo(this);}
function Bo(){if(this.h!==null){return this.h.eb();}return false;}
function Co(){this.h.ib();this.pb();}
function Do(){try{this.vb();}finally{this.h.mb();}}
function wo(){}
_=wo.prototype=new rB();_.D=Ao;_.eb=Bo;_.ib=Co;_.mb=Do;_.tN=qL+'Composite';_.tI=19;_.h=null;function xc(a){a.a=ub('[Lcom.google.gwt.sample.mail.client.Contacts$Contact;',126,22,[uc(new qc(),'Benoit Mandelbrot','benoit@example.com',a),uc(new qc(),'Albert Einstein','albert@example.com',a),uc(new qc(),'Rene Descartes','rene@example.com',a),uc(new qc(),'Bob Saget','bob@example.com',a),uc(new qc(),'Ludwig von Beethoven','ludwig@example.com',a),uc(new qc(),'Richard Feynman','richard@example.com',a),uc(new qc(),'Alan Turing','alan@example.com',a),uc(new qc(),'John von Neumann','john@example.com',a)]);a.b=lB(new jB());}
function yc(d,b){var a,c;xc(d);c=wx(new px());c.dc(d.b);for(a=0;a<d.a.a;++a){zc(d,d.a[a]);}zo(d,c);DA(d,'mail-Contacts');return d;}
function zc(c,a){var b;b=lt(new Eq(),"<a href='javascript:;'>"+a.b+'<\/a>');mB(c.b,b);jv(b,nc(new mc(),c,a,b));}
function lc(){}
_=lc.prototype=new wo();_.tN=nL+'Contacts';_.tI=20;function nc(b,a,c,d){b.a=a;b.b=c;b.c=d;return b;}
function pc(c){var a,b,d;b=sc(new rc(),this.b,this.a);a=wA(this.c)+14;d=xA(this.c)+14;ow(b,a,d);sw(b);}
function mc(){}
_=mc.prototype=new sF();_.lb=pc;_.tN=nL+'Contacts$1';_.tI=21;function uc(d,b,a,c){d.b=b;d.a=a;return d;}
function qc(){}
_=qc.prototype=new sF();_.tN=nL+'Contacts$Contact';_.tI=22;_.a=null;_.b=null;function tc(){tc=kL;hw();}
function sc(g,a,f){var b,c,d,e;tc();dw(g,true);d=lB(new jB());e=iv(new gv(),a.b);b=iv(new gv(),a.a);mB(d,e);mB(d,b);c=fu(new du());vn(c,4);gu(c,mD((ke(),ne)));gu(c,d);yx(g,c);DA(g,'mail-ContactPopup');DA(e,'mail-ContactPopupName');DA(b,'mail-ContactPopupEmail');return g;}
function rc(){}
_=rc.prototype=new bw();_.tN=nL+'Contacts$ContactPopup';_.tI=23;function ee(){ee=kL;Ce=je(new ie());}
function ce(a){a.e=Af(new xf(),Ce);a.c=lB(new jB());a.a=dd(new bd());a.d=nf(new ff(),Ce);}
function de(a){ee();ce(a);return a;}
function fe(b,a){gd(b.a,a);}
function ge(b){var a;Ee=b;b.e.ec('100%');b.b=Ad(new yd());b.b.ec('100%');mB(b.c,b.b);mB(b.c,b.a);b.b.ec('100%');b.a.ec('100%');a=yp(new pp());zp(a,b.e,(Ap(),cq));zp(a,b.d,(Ap(),eq));zp(a,b.c,(Ap(),aq));a.ec('100%');vn(a,4);Fp(a,b.c,'100%');hk(b);kk(false);xk('0px');Dm(fx(),a);fj(Dc(new Cc(),b));he(b,pk(),ok());}
function he(c,d,a){var b;b=a-xA(c.d)-8;if(b<1){b=1;}c.d.ac(''+b);ed(c.a,d,a);}
function De(b,a){he(this,b,a);}
function Bc(){}
_=Bc.prototype=new sF();_.yb=De;_.tN=nL+'Mail';_.tI=24;_.b=null;var Ce,Ee=null;function Dc(b,a){b.a=a;return b;}
function Fc(){he(this.a,pk(),ok());}
function Cc(){}
_=Cc.prototype=new sF();_.A=Fc;_.tN=nL+'Mail$1';_.tI=25;function cd(a){a.c=lB(new jB());a.b=lB(new jB());a.g=kt(new Eq());a.f=kt(new Eq());a.d=kt(new Eq());a.a=kt(new Eq());a.e=lx(new jx(),a.a);}
function dd(b){var a;cd(b);nv(b.a,true);mB(b.b,b.g);mB(b.b,b.f);mB(b.b,b.d);b.b.ec('100%');a=yp(new pp());zp(a,b.b,(Ap(),cq));zp(a,b.e,(Ap(),aq));Cp(a,b.e,'100%');mB(b.c,a);CA(a,'100%','100%');CA(b.e,'100%','100%');zo(b,b.c);DA(b,'mail-Detail');DA(b.b,'mail-DetailHeader');DA(a,'mail-DetailInner');DA(b.g,'mail-DetailSubject');DA(b.f,'mail-DetailSender');DA(b.d,'mail-DetailRecipient');DA(b.a,'mail-DetailBody');return b;}
function ed(c,e,d){var a,b;b=e-wA(c.e)-9;if(b<1){b=1;}a=d-xA(c.e)-9;if(a<1){a=1;}CA(c.e,''+b,''+a);}
function gd(b,a){ot(b.g,a.e);ot(b.f,'<b>From:<\/b>&nbsp;'+a.d);ot(b.d,'<b>To:<\/b>&nbsp;foo@example.com');ot(b.a,a.a);}
function bd(){}
_=bd.prototype=new wo();_.tN=nL+'MailDetail';_.tI=26;function id(e,c,b,d,a){e.d=c;e.b=b;e.e=d;e.a=a;return e;}
function hd(){}
_=hd.prototype=new sF();_.tN=nL+'MailItem';_.tI=27;_.a=null;_.b=null;_.c=false;_.d=null;_.e=null;function ld(){ld=kL;var a;vd=ub('[Ljava.lang.String;',127,1,['markboland05','Hollie Voss','boticario','Emerson Milton','Healy Colette','Brigitte Cobb','Elba Lockhart','Claudio Engle','Dena Pacheco','Brasil s.p','Parker','derbvktqsr','qetlyxxogg','antenas.sul','Christina Blake','Gail Horton','Orville Daniel','PostMaster','Rae Childers','Buster misjenou','user31065','ftsgeolbx','aqlovikigd','user18411','Mildred Starnes','Candice Carson','Louise Kelchner','Emilio Hutchinson','Geneva Underwood','Residence Oper?','fpnztbwag','tiger','Heriberto Rush','bulrush Bouchard','Abigail Louis','Chad Andrews','bjjycpaa','Terry English','Bell Snedden','huang','hhh','(unknown sender)','Kent','Dirk Newman','Equipe Virtual Cards','wishesundmore','Benito Meeks']);od=ub('[Ljava.lang.String;',127,1,['mark@example.com','hollie@example.com','boticario@example.com','emerson@example.com','healy@example.com','brigitte@example.com','elba@example.com','claudio@example.com','dena@example.com','brasilsp@example.com','parker@example.com','derbvktqsr@example.com','qetlyxxogg@example.com','antenas_sul@example.com','cblake@example.com','gailh@example.com','orville@example.com','post_master@example.com','rchilders@example.com','buster@example.com','user31065@example.com','ftsgeolbx@example.com','aqlovikigd@example.com','user18411@example.com','mildred@example.com','candice@example.com','louise_kelchner@example.com','emilio@example.com','geneva@example.com','residence_oper@example.com','fpnztbwag@example.com','tiger@example.com','heriberto@example.com','bulrush@example.com','abigail_louis@example.com','chada@example.com','bjjycpaa@example.com','terry@example.com','bell@example.com','huang@example.com','hhh@example.com','kent@example.com','newman@example.com','equipe_virtual@example.com','wishesundmore@example.com','benito@example.com']);xd=ub('[Ljava.lang.String;',127,1,['URGENT -[Mon, 24 Apr 2006 02:17:27 +0000]','URGENT TRANSACTION -[Sun, 23 Apr 2006 13:10:03 +0000]','fw: Here it comes','voce ganho um vale presente Boticario','Read this ASAP','Hot Stock Talk','New Breed of Equity Trader','FWD: TopWeeks the wire special pr news release','[fwd] Read this ASAP','Renda Extra R$1.000,00-R$2.000,00/m?s','re: Make sure your special pr news released','Forbidden Knowledge Conference','decodificadores os menores pre?os','re: Our Pick','RE: The hottest pick Watcher','RE: St0kkMarrkett Picks Trade watch special pr news release','St0kkMarrkett Picks Watch special pr news release news','You are a Winner oskoxmshco','Encrypted E-mail System (VIRUS REMOVED)','Fw: Malcolm','Secure Message System (VIRUS REMOVED)','fwd: St0kkMarrkett Picks Watch special pr news releaser','FWD: Financial Market Traderr special pr news release','? s? uma dica r?pida !!!!! leia !!!','re: You have to heard this','fwd: Watcher TopNews','VACANZE alle Mauritius','funny','re: You need to review this','[re:] Our Pick','RE: Before the be11 special pr news release','[re:] Market TradePicks Trade watch news','No prescription needed','Seu novo site','[fwd] Financial Market Trader Picker','FWD: Top Financial Market Specialists Trader interest increases','Os cart?es mais animados da web!!','We will sale 4 you cebtdbwtcv','RE: Best Top Financial Market Specialists Trader Picks']);qd=ub('[Ljava.lang.String;',127,1,['Dear Friend,<br><br>I am Mr. Mark Boland the Bank Manager of ABN AMRO BANK 101 Moorgate, London, EC2M 6SB.<br><br>','I have an urgent and very confidential business proposition for you. On July 20, 2001; Mr. Zemenu Gente, a National of France, who used to be a private contractor with the Shell Petroleum Development Company in Saudi Arabia. Mr. Zemenu Gente Made a Numbered time (Fixed deposit) for 36 calendar months, valued at GBP?30, 000,000.00 (Thirty Million Pounds only) in my Branch.','I have all necessary legal documents that can be used to back up any claim we may make. All I require is your honest Co-operation, Confidentiality and A trust to enable us sees this transaction through. I guarantee you that this will be executed under a legitimate arrangement that will protect you from any breach of the law. Please get in touch with me urgently by E-mail and Provide me with the following;<br>','The OIL sector is going crazy. This is our weekly gift to you!<br><br>Get KKPT First Thing, This Is Going To Run!<br><br>Check out Latest NEWS!<br><br>KOKO PETROLEUM (KKPT) - This is our #1 pick for next week!<br>Our last pick gained $2.16 in 4 days of trading.<br>','LAS VEGAS, NEVADA--(MARKET WIRE)--Apr 6, 2006 -- KOKO Petroleum, Inc. (Other OTC:KKPT.PK - News) -<br>KOKO Petroleum, Inc. announced today that its operator for the Corsicana Field, JMT Resources, Ltd. ("JMT") will commence a re-work program on its Pecan Gap wells in the next week. The re-work program will consist of drilling six lateral bore production strings from the existing well bore. This process, known as Radial Jet Enhancement, will utilize high pressure fluids to drill the lateral well bores, which will extend out approximately 350\' each.','JMT has contracted with Well Enhancement Services, LLC (www.wellenhancement.com) to perform the rework on its Pierce nos. 14 and 14a. A small sand frac will follow the drilling of the lateral well bores in order to enhance permeability and create larger access to the Pecan Gap reservoir. Total cost of the re-work per well is estimated to be approximately $50,000 USD.','Parab?ns!<br>Voc? Ganhou Um Vale Presente da Botic?rio no valor de R$50,00<br>Voc? foi contemplado na Promo??o Respeite Minha Natureza - Pulseira Social.<br>Algu?m pode t?-lo inscrito na promo??o! (Amigos(as), Namorado(a) etc.).<br>Para retirar o seu pr?mio em uma das nossas Lojas, fa?a o download do Vale-Presente abaixo.<br>Ap?s o download, com o arquivo previamente salvo, imprima uma folha e salve a c?pia em seu computador para evitar transtornos decorrentes da perda do mesmo. Lembramos que o Vale-Presente ? ?nico e intransfer?vel.','Large Marketing Campaign running this weekend!<br><br>Should you get in today before it explodes?<br><br>This Will Fly Starting Monday!','PREMIER INFORMATION (PIFR)<br>A U.S. based company offers specialized information management serices to both the Insurance and Healthcare Industries. The services we provide are specific to each industry and designed for quick response and maximum security.<br><br>STK- PIFR<br>Current Price: .20<br>This one went to $2.80 during the last marketing Campaign!','These partnerships specifically allow Premier to obtain personal health information, as governed by the Health In-surancee Portability and Accountability Act of 1996 (HIPAA), and other applicable state laws and regulations.<br><br>Global HealthCare Market Undergoing Digital Conversion','>>   Componentes e decodificadores; confira aqui;<br> http://br.geocities.com/listajohn/index.htm<br>','THE GOVERNING AWARD<br>NETHERLANDS HEAD OFFICE<br>AC 76892 HAUITSOP<br>AMSTERDAM, THE NETHERLANDS.<br>FROM: THE DESK OF THE PROMOTIONS MANAGER.<br>INTERNATIONAL PROMOTIONS / PRIZE AWARD DEPARTMENT<br>REF NUMBER: 14235/089.<br>BATCH NUMBER: 304/64780/IFY.<br>RE/AWARD NOTIFICATION<br>',"We are pleased to inform you of the announcement today 13th of April 2006, you among TWO LUCKY WINNERS WON the GOVERNING AWARD draw held on the 28th of March 2006. The THREE Winning Addresses were randomly selected from a batch of 10,000,000 international email addresses. Your email address emerged alongside TWO others as a category B winner in this year's Annual GOVERNING AWARD Draw.<br>",'>> obrigado por me dar esta pequena aten??o !!!<br>CASO GOSTE DE ASSISTIR TV , MAS A SUA ANTENA S? PEGA AQUELES CANAIS LOCAIS  OU O SEU SISTEMA PAGO ? MUITO CARO , SAIBA QUE TENHO CART?ES DE ACESSO PARA SKY DIRECTV , E DECODERS PARA  NET TVA E TECSAT , TUDO GRATIS , SEM ASSINTURA , SEM MENSALIDADE, VC PAGA UMA VEZ S? E ASSISTE A MUITOS CANAIS , FILMES , JOGOS , PORNOS , DESENHOS , DOCUMENT?RIOS ,SHOWS , ETC,<br><br>CART?O SKY E DIRECTV TOTALMENTE HACKEADOS  350,00<br>DECODERS NET TVA DESBLOQUEADOS                       390,00<br>KITS COMPLETOS SKY OU DTV ANTENA DECODER E CART?O  650,00<br>TECSAT FREE   450,00<br>TENHO TB ACESS?RIOS , CABOS, LNB .<br>','********************************************************************<br> Original filename: mail.zip<br> Virus discovered: JS.Feebs.AC<br>********************************************************************<br> A file that was attached to this email contained a virus.<br> It is very likely that the original message was generated<br> by the virus and not a person - treat this message as you would<br> any other junk mail (spam).<br> For more information on why you received this message please visit:<br>','Put a few letters after your name. Let us show you how you can do it in just a few days.<br><br>http://thewrongchoiceforyou.info<br><br>kill future mailing by pressing this : see main website',"We possess scores of pharmaceutical products handy<br>All med's are made in U.S. laboratories<br>For your wellbeing! Very rapid, protected and secure<br>Ordering, No script required. We have the pain aid you require<br>",'"Oh, don\'t speak to me of Austria. Perhaps I don\'t understand things, but Austria never has wished, and does not wish, for war. She is betraying us! Russia alone must save Europe. Our gracious sovereign recognizes his high vocation and will be true to it. That is the one thing I have faith in! Our good and wonderful sovereign has to perform the noblest role on earth, and he is so virtuous and noble that God will not forsake him. He will fulfill his vocation and crush the hydra of revolution, which has become more terrible than ever in the person of this murderer and villain! We alone must avenge the blood of the just one.... Whom, I ask you, can we rely on?... England with her commercial spirit will not and cannot understand the Emperor Alexander\'s loftiness of soul. She has refused to evacuate Malta. She wanted to find, and still seeks, some secret motive in our actions. What answer did Novosiltsev get? None. The English have not understood and cannot understand the self-ab!<br>negation of our Emperor who wants nothing for himself, but only desires the good of mankind. And what have they promised? Nothing! And what little they have promised they will not perform! Prussia has always declared that Buonaparte is invincible, and that all Europe is powerless before him.... And I don\'t believe a word that Hardenburg says, or Haugwitz either. This famous Prussian neutrality is just a trap. I have faith only in God and the lofty destiny of our adored monarch. He will save Europe!"<br>"Those were extremes, no doubt, but they are not what is most important. What is important are the rights of man, emancipation from prejudices, and equality of citizenship, and all these ideas Napoleon has retained in full force."']);td=yI(new wI());{for(a=0;a<37;++a){zI(td,md());}}}
function md(){ld();var a,b,c,d,e;d=vd[ud++];if(ud==vd.a){ud=0;}b=od[nd++];if(nd==od.a){nd=0;}e=xd[wd++];if(wd==xd.a){wd=0;}a='';for(c=0;c<10;++c){a+=qd[pd++];if(pd==qd.a){pd=0;}}return id(new hd(),d,b,e,a);}
function sd(a){ld();if(a>=td.b){return null;}return zb(DI(td,a),4);}
function rd(){ld();return td.b;}
var nd=0,od,pd=0,qd,td,ud=0,vd,wd=0,xd;function zd(a){a.a=kt(new Eq());a.c=mt(new Eq(),"<a href='javascript:;'>&lt; newer<\/a>",true);a.d=mt(new Eq(),"<a href='javascript:;'>older &gt;<\/a>",true);a.g=mq(new hq());a.b=fu(new du());}
function Ad(b){var a;zd(b);bt(b.g,0);at(b.g,0);b.g.ec('100%');ss(b.g,b);jv(b.c,b);jv(b.d,b);a=fu(new du());DA(b.b,'mail-ListNavBar');gu(a,b.c);gu(a,b.a);gu(a,b.d);ju(b.b,(vt(),yt));gu(b.b,a);b.b.ec('100%');zo(b,b.g);DA(b,'mail-List');Cd(b);Fd(b);return b;}
function Cd(b){var a;ft(b.g,0,0,'Sender');ft(b.g,0,1,'Email');ft(b.g,0,2,'Subject');gt(b.g,0,3,b.b);as(b.g.d,0,'mail-ListHeader');for(a=0;a<10;++a){ft(b.g,a+1,0,'');ft(b.g,a+1,1,'');ft(b.g,a+1,2,'');ur(b.g.b,a+1,0,false);ur(b.g.b,a+1,1,false);ur(b.g.b,a+1,2,false);lq(b.g.b,a+1,2,2);}}
function Dd(c,b){var a;a=sd(c.f+b);if(a===null){return;}Ed(c,c.e,false);Ed(c,b,true);a.c=true;c.e=b;fe((ee(),Ee),a);}
function Ed(c,a,b){if(a!=(-1)){if(b){Br(c.g.d,a+1,'mail-SelectedRow');}else{Fr(c.g.d,a+1,'mail-SelectedRow');}}}
function Fd(e){var a,b,c,d;a=rd();d=e.f+10;if(d>a){d=a;}e.c.cc(e.f!=0);e.d.cc(e.f+10<a);mv(e.a,''+(e.f+1)+' - '+d+' of '+a);b=0;for(;b<10;++b){if(e.f+b>=rd()){break;}c=sd(e.f+b);ft(e.g,b+1,0,c.d);ft(e.g,b+1,1,c.b);ft(e.g,b+1,2,c.e);}for(;b<10;++b){dt(e.g,b+1,0,'&nbsp;');dt(e.g,b+1,1,'&nbsp;');dt(e.g,b+1,2,'&nbsp;');}if(e.e==(-1)){Dd(e,0);}}
function ae(c,b,a){if(b>0){Dd(this,b-1);}}
function be(a){if(a===this.d){this.f+=10;if(this.f>=rd()){this.f-=10;}else{Ed(this,this.e,false);this.e=(-1);Fd(this);}}else if(a===this.c){this.f-=10;if(this.f<0){this.f=0;}else{Ed(this,this.e,false);this.e=(-1);Fd(this);}}}
function yd(){}
_=yd.prototype=new wo();_.kb=ae;_.lb=be;_.tN=nL+'MailList';_.tI=28;_.e=(-1);_.f=0;function ke(){ke=kL;le=v()+'B9DA8B0768BAD7283674A8E1D92AD03D.cache.png';me=jD(new iD(),le,0,0,32,32);ne=jD(new iD(),le,32,0,32,32);oe=jD(new iD(),le,64,0,16,16);pe=jD(new iD(),le,80,0,16,16);qe=jD(new iD(),le,96,0,16,16);re=jD(new iD(),le,112,0,4,4);se=jD(new iD(),le,116,0,140,75);te=jD(new iD(),le,256,0,32,32);ue=jD(new iD(),le,288,0,4,4);ve=jD(new iD(),le,292,0,16,16);we=jD(new iD(),le,308,0,32,32);xe=jD(new iD(),le,340,0,16,16);ye=jD(new iD(),le,356,0,16,16);ze=jD(new iD(),le,372,0,16,16);Ae=jD(new iD(),le,388,0,16,16);Be=jD(new iD(),le,404,0,16,16);}
function je(a){ke();return a;}
function ie(){}
_=ie.prototype=new sF();_.tN=nL+'Mail_Images_generatedBundle';_.tI=29;var le,me,ne,oe,pe,qe,re,se,te,ue,ve,we,xe,ye,ze,Ae,Be;function bf(c,a){var b;c.a=Cz(new Dy(),a);b=jz(new gz(),ef(c,(ke(),pe),'foo@example.com'));Dz(c.a,b);cf(c,b,'Inbox',(ke(),qe));cf(c,b,'Drafts',(ke(),oe));cf(c,b,'Templates',(ke(),xe));cf(c,b,'Sent',(ke(),ve));cf(c,b,'Trash',(ke(),ye));tz(b,true);zo(c,c.a);return c;}
function cf(d,c,e,a){var b;b=jz(new gz(),ef(d,a,e));c.s(b);return b;}
function ef(b,a,c){return '<span>'+nD(a)+c+'<\/span>';}
function Fe(){}
_=Fe.prototype=new wo();_.tN=nL+'Mailboxes';_.tI=30;_.a=null;function mf(a){a.b=hf(new gf(),a);}
function nf(b,a){mf(b);of(b,a,bf(new Fe(),a),(ke(),te),'Mail');of(b,a,vf(new uf()),(ke(),we),'Tasks');of(b,a,yc(new lc(),a),(ke(),me),'Contacts');zo(b,b.b);return b;}
function of(d,c,e,b,a){uA(e,'mail-StackContent');my(d.b,e,rf(d,c,b,a),true);}
function qf(b,a){return 'header-'+b.hC()+'-'+a;}
function rf(g,e,d,a){var b,c,f;f=g.a==0;c=qf(g,g.a);g.a++;b="<table class='caption' cellpadding='0' cellspacing='0'><tr><td class='lcaption'>"+nD(d)+"<\/td><td class='rcaption'><b style='white-space:nowrap'>"+a+'<\/b><\/td><\/tr><\/table>';return "<table id='"+c+"' align='left' cellpadding='0' cellspacing='0'"+(f?" class='is-top'":'')+'><tbody>'+"<tr><td class='box-00'>"+nD((ke(),re))+'<\/td>'+"<td class='box-10'>&nbsp;<\/td>"+"<td class='box-20'>"+nD((ke(),ue))+'<\/td>'+'<\/tr><tr>'+"<td class='box-01'>&nbsp;<\/td>"+"<td class='box-11'>"+b+'<\/td>'+"<td class='box-21'>&nbsp;<\/td>"+'<\/tr><\/tbody><\/table>';}
function sf(d,c,b){var a;c++;if(c>0&&c<d.b.f.b){a=fi(qf(d,c));zi(a,'className','');}b++;if(b>0&&b<d.b.f.b){a=fi(qf(d,b));zi(a,'className','is-beneath-selected');}}
function tf(){uy(this.b,0);sf(this,(-1),0);}
function ff(){}
_=ff.prototype=new wo();_.pb=tf;_.tN=nL+'Shortcuts';_.tI=31;_.a=0;function ko(a){a.f=AB(new sB(),a);}
function lo(a){ko(a);return a;}
function mo(c,a,b){rC(a);BB(c.f,a);ch(b,a.D());Av(c,a);}
function no(d,b,a){var c;oo(d,a);if(b.q===d){c=qo(d,b);if(c<a){a--;}}return a;}
function oo(b,a){if(a<0||a>b.f.b){throw new wE();}}
function ro(b,a){return DB(b.f,a);}
function qo(b,a){return EB(b.f,a);}
function so(e,b,c,a,d){a=no(e,b,a);rC(b);FB(e.f,b,a);if(d){ni(c,yo(b),a);}else{ch(c,yo(b));}Av(e,b);}
function to(b,c){var a;if(c.q!==b){return false;}Cv(b,c);a=c.D();ri(li(a),a);cC(b.f,c);return true;}
function uo(){return aC(this.f);}
function vo(a){return to(this,a);}
function jo(){}
_=jo.prototype=new zv();_.fb=uo;_.Bb=vo;_.tN=qL+'ComplexPanel';_.tI=32;function ky(b){var a;lo(b);a=oh();b.Eb(a);b.b=lh();ch(a,b.b);yi(a,'cellSpacing',0);yi(a,'cellPadding',0);aj(a,1);DA(b,'gwt-StackPanel');return b;}
function ly(a,b){py(a,b,a.f.b);}
function my(c,d,b,a){ly(c,d);sy(c,c.f.b-1,b,a);}
function oy(d,a){var b,c;while(a!==null&& !dh(a,d.D())){b=ii(a,'__index');if(b!==null){c=hi(a,'__owner');if(c==d.hC()){return DE(b);}else{return (-1);}}a=li(a);}return (-1);}
function py(e,h,a){var b,c,d,f,g;g=nh();d=mh();ch(g,d);f=nh();c=mh();ch(f,c);a=no(e,h,a);b=a*2;ni(e.b,f,b);ni(e.b,g,b);fB(d,'gwt-StackPanelItem',true);yi(d,'__owner',e.hC());zi(d,'height','1px');zi(c,'height','100%');zi(c,'vAlign','top');so(e,h,c,a,false);vy(e,a);if(e.c==(-1)){uy(e,0);}else{ty(e,a,false);if(e.c>=a){++e.c;}}}
function qy(d,a){var b,c;if(Eh(a)==1){c=Ch(a);b=oy(d,c);if(b!=(-1)){uy(d,b);}}}
function ry(e,a,b){var c,d,f;c=to(e,a);if(c){d=2*b;f=ei(e.b,d);ri(e.b,f);f=ei(e.b,d);ri(e.b,f);if(e.c==b){e.c=(-1);}else if(e.c>b){--e.c;}vy(e,d);}return c;}
function sy(e,b,d,a){var c;if(b>=e.f.b){return;}c=ei(ei(e.b,b*2),0);if(a){Ci(c,d);}else{Di(c,d);}}
function ty(c,a,e){var b,d;d=ei(c.b,a*2);if(d===null){return;}b=ki(d);fB(b,'gwt-StackPanelItem-selected',e);d=ei(c.b,a*2+1);gB(d,e);ro(c,a).cc(e);}
function uy(b,a){if(a>=b.f.b||a==b.c){return;}if(b.c>=0){ty(b,b.c,false);}b.c=a;ty(b,b.c,true);}
function vy(f,a){var b,c,d,e;for(e=a,b=f.f.b;e<b;++e){d=ei(f.b,e*2);c=ki(d);yi(c,'__index',e);}}
function wy(a){qy(this,a);}
function xy(a){return ry(this,a,qo(this,a));}
function jy(){}
_=jy.prototype=new jo();_.jb=wy;_.Bb=xy;_.tN=qL+'StackPanel';_.tI=33;_.b=null;_.c=(-1);function hf(b,a){b.a=a;ky(b);return b;}
function kf(a){var b,c;c=this.c;qy(this,a);b=this.c;if(c!=b){sf(this.a,c,b);}}
function gf(){}
_=gf.prototype=new jy();_.jb=kf;_.tN=nL+'Shortcuts$1';_.tI=34;function vf(c){var a,b;b=wx(new px());a=lB(new jB());b.dc(a);mB(a,An(new xn(),'Get groceries'));mB(a,An(new xn(),'Walk the dog'));mB(a,An(new xn(),'Start Web 2.0 company'));mB(a,An(new xn(),'Write cool app in GWT'));mB(a,An(new xn(),'Get funding'));mB(a,An(new xn(),'Take a vacation'));zo(c,b);DA(c,'mail-Tasks');return c;}
function uf(){}
_=uf.prototype=new wo();_.tN=nL+'Tasks';_.tI=35;function zf(a){a.b=lt(new Eq(),"<a href='javascript:;'>Sign Out<\/a>");a.a=lt(new Eq(),"<a href='javascript:;'>About<\/a>");}
function Af(f,a){var b,c,d,e;zf(f);e=fu(new du());b=lB(new jB());ju(e,(vt(),yt));pB(b,(vt(),yt));c=fu(new du());vn(c,4);gu(c,f.b);gu(c,f.a);d=mD((ke(),se));gu(e,d);e.Db(d,(vt(),xt));gu(e,b);mB(b,lt(new Eq(),'<b>Welcome back, foo@example.com<\/b>'));mB(b,c);jv(f.b,f);jv(f.a,f);zo(f,e);DA(f,'mail-TopPanel');DA(c,'mail-TopPanelLinks');return f;}
function Cf(b){var a;if(b===this.b){ik('If this were implemented, you would be signed out now.');}else if(b===this.a){a=ic(new dc());sw(a);gw(a);}}
function xf(){}
_=xf.prototype=new wo();_.lb=Cf;_.tN=nL+'TopPanel';_.tI=36;function oG(b,a){a;return b;}
function nG(){}
_=nG.prototype=new sF();_.tN=sL+'Throwable';_.tI=3;function oE(b,a){oG(b,a);return b;}
function nE(){}
_=nE.prototype=new nG();_.tN=sL+'Exception';_.tI=4;function xF(b,a){oE(b,a);return b;}
function wF(){}
_=wF.prototype=new nE();_.tN=sL+'RuntimeException';_.tI=5;function Ef(b,a){return b;}
function Df(){}
_=Df.prototype=new wF();_.tN=oL+'CommandCanceledException';_.tI=37;function ug(a){a.a=cg(new bg(),a);a.b=yI(new wI());a.d=gg(new fg(),a);a.f=kg(new jg(),a);}
function vg(a){ug(a);return a;}
function xg(c){var a,b,d;a=mg(c.f);pg(c.f);b=null;if(Ab(a,5)){b=Ef(new Df(),zb(a,5));}else{}if(b!==null){d=x;}Ag(c,false);zg(c);}
function yg(e,d){var a,b,c,f;f=false;try{Ag(e,true);qg(e.f,e.b.b);Bj(e.a,10000);while(ng(e.f)){b=og(e.f);c=true;try{if(b===null){return;}if(Ab(b,5)){a=zb(b,5);a.A();}else{}}finally{f=rg(e.f);if(f){return;}if(c){pg(e.f);}}if(Dg(lG(),d)){return;}}}finally{if(!f){yj(e.a);Ag(e,false);zg(e);}}}
function zg(a){if(!aJ(a.b)&& !a.e&& !a.c){Bg(a,true);Bj(a.d,1);}}
function Ag(b,a){b.c=a;}
function Bg(b,a){b.e=a;}
function Cg(b,a){zI(b.b,a);zg(b);}
function Dg(a,b){return bF(a-b)>=100;}
function ag(){}
_=ag.prototype=new sF();_.tN=oL+'CommandExecutor';_.tI=38;_.c=false;_.e=false;function zj(){zj=kL;bk=yI(new wI());{ak();}}
function xj(a){zj();return a;}
function yj(a){if(a.b){Cj(a.c);}else{Dj(a.c);}cJ(bk,a);}
function Aj(a){if(!a.b){cJ(bk,a);}a.Cb();}
function Bj(b,a){if(a<=0){throw rE(new qE(),'must be positive');}yj(b);b.b=false;b.c=Ej(b,a);zI(bk,b);}
function Cj(a){zj();$wnd.clearInterval(a);}
function Dj(a){zj();$wnd.clearTimeout(a);}
function Ej(b,a){zj();return $wnd.setTimeout(function(){b.B();},a);}
function Fj(){var a;a=x;{Aj(this);}}
function ak(){zj();gk(new tj());}
function sj(){}
_=sj.prototype=new sF();_.B=Fj;_.tN=oL+'Timer';_.tI=39;_.b=false;_.c=0;var bk;function dg(){dg=kL;zj();}
function cg(b,a){dg();b.a=a;xj(b);return b;}
function eg(){if(!this.a.c){return;}xg(this.a);}
function bg(){}
_=bg.prototype=new sj();_.Cb=eg;_.tN=oL+'CommandExecutor$1';_.tI=40;function hg(){hg=kL;zj();}
function gg(b,a){hg();b.a=a;xj(b);return b;}
function ig(){Bg(this.a,false);yg(this.a,lG());}
function fg(){}
_=fg.prototype=new sj();_.Cb=ig;_.tN=oL+'CommandExecutor$2';_.tI=41;function kg(b,a){b.d=a;return b;}
function mg(a){return DI(a.d.b,a.b);}
function ng(a){return a.c<a.a;}
function og(b){var a;b.b=b.c;a=DI(b.d.b,b.c++);if(b.c>=b.a){b.c=0;}return a;}
function pg(a){bJ(a.d.b,a.b);--a.a;if(a.b<=a.c){if(--a.c<0){a.c=0;}}a.b=(-1);}
function qg(b,a){b.a=a;}
function rg(a){return a.b==(-1);}
function sg(){return ng(this);}
function tg(){return og(this);}
function jg(){}
_=jg.prototype=new sF();_.db=sg;_.hb=tg;_.tN=oL+'CommandExecutor$CircularIterator';_.tI=42;_.a=0;_.b=(-1);_.c=0;function ah(){ah=kL;ui=yI(new wI());{mi=new zk();kl(mi);}}
function bh(a){ah();zI(ui,a);}
function ch(b,a){ah();vl(mi,b,a);}
function dh(a,b){ah();return Bk(mi,a,b);}
function eh(){ah();return xl(mi,'button');}
function fh(){ah();return xl(mi,'div');}
function gh(a){ah();return xl(mi,a);}
function hh(){ah();return xl(mi,'img');}
function ih(){ah();return yl(mi,'checkbox');}
function jh(){ah();return xl(mi,'label');}
function kh(){ah();return xl(mi,'span');}
function lh(){ah();return xl(mi,'tbody');}
function mh(){ah();return xl(mi,'td');}
function nh(){ah();return xl(mi,'tr');}
function oh(){ah();return xl(mi,'table');}
function rh(b,a,d){ah();var c;c=x;{qh(b,a,d);}}
function qh(b,a,c){ah();var d;if(a===ti){if(Eh(b)==8192){ti=null;}}d=ph;ph=b;try{c.jb(b);}finally{ph=d;}}
function sh(b,a){ah();zl(mi,b,a);}
function th(a){ah();return Al(mi,a);}
function uh(a){ah();return Ck(mi,a);}
function vh(a){ah();return Dk(mi,a);}
function wh(a){ah();return Bl(mi,a);}
function xh(a){ah();return Ek(mi,a);}
function yh(a){ah();return Fk(mi,a);}
function zh(a){ah();return Cl(mi,a);}
function Ah(a){ah();return Dl(mi,a);}
function Bh(a){ah();return El(mi,a);}
function Ch(a){ah();return al(mi,a);}
function Dh(a){ah();return bl(mi,a);}
function Eh(a){ah();return Fl(mi,a);}
function Fh(a){ah();cl(mi,a);}
function ai(a){ah();return dl(mi,a);}
function bi(a){ah();return el(mi,a);}
function ei(b,a){ah();return hl(mi,b,a);}
function ci(a){ah();return fl(mi,a);}
function di(b,a){ah();return gl(mi,b,a);}
function fi(a){ah();return am(mi,a);}
function ii(a,b){ah();return dm(mi,a,b);}
function gi(a,b){ah();return bm(mi,a,b);}
function hi(a,b){ah();return cm(mi,a,b);}
function ji(a){ah();return em(mi,a);}
function ki(a){ah();return il(mi,a);}
function li(a){ah();return jl(mi,a);}
function ni(c,a,b){ah();ll(mi,c,a,b);}
function oi(b,a){ah();return ml(mi,b,a);}
function pi(a){ah();var b,c;c=true;if(ui.b>0){b=zb(DI(ui,ui.b-1),6);if(!(c=b.nb(a))){sh(a,true);Fh(a);}}return c;}
function qi(a){ah();if(ti!==null&&dh(a,ti)){ti=null;}nl(mi,a);}
function ri(b,a){ah();fm(mi,b,a);}
function si(a){ah();cJ(ui,a);}
function vi(a){ah();gm(mi,a);}
function wi(a){ah();ti=a;ol(mi,a);}
function zi(a,b,c){ah();jm(mi,a,b,c);}
function xi(a,b,c){ah();hm(mi,a,b,c);}
function yi(a,b,c){ah();im(mi,a,b,c);}
function Ai(a,b){ah();km(mi,a,b);}
function Bi(a,b){ah();pl(mi,a,b);}
function Ci(a,b){ah();lm(mi,a,b);}
function Di(a,b){ah();ql(mi,a,b);}
function Ei(b,a,c){ah();mm(mi,b,a,c);}
function Fi(b,a,c){ah();nm(mi,b,a,c);}
function aj(a,b){ah();rl(mi,a,b);}
function bj(){ah();return om(mi);}
function cj(){ah();return pm(mi);}
var ph=null,mi=null,ti=null,ui;function ej(){ej=kL;gj=vg(new ag());}
function fj(a){ej();if(a===null){throw gF(new fF(),'cmd can not be null');}Cg(gj,a);}
var gj;function jj(b,a){if(Ab(a,7)){return dh(b,zb(a,7));}return cb(ac(b,hj),a);}
function kj(a){return jj(this,a);}
function lj(){return db(ac(this,hj));}
function hj(){}
_=hj.prototype=new ab();_.eQ=kj;_.hC=lj;_.tN=oL+'Element';_.tI=43;function qj(a){return cb(ac(this,mj),a);}
function rj(){return db(ac(this,mj));}
function mj(){}
_=mj.prototype=new ab();_.eQ=qj;_.hC=rj;_.tN=oL+'Event';_.tI=44;function vj(){while((zj(),bk).b>0){yj(zb(DI((zj(),bk),0),8));}}
function wj(){return null;}
function tj(){}
_=tj.prototype=new sF();_.wb=vj;_.xb=wj;_.tN=oL+'Timer$1';_.tI=45;function fk(){fk=kL;jk=yI(new wI());wk=yI(new wI());{sk();}}
function gk(a){fk();zI(jk,a);}
function hk(a){fk();zI(wk,a);}
function ik(a){fk();$wnd.alert(a);}
function kk(a){fk();$doc.body.style.overflow=a?'auto':'hidden';}
function lk(){fk();var a,b;for(a=eH(jk);DG(a);){b=zb(EG(a),9);b.wb();}}
function mk(){fk();var a,b,c,d;d=null;for(a=eH(jk);DG(a);){b=zb(EG(a),9);c=b.xb();{d=c;}}return d;}
function nk(){fk();var a,b;for(a=eH(wk);DG(a);){b=zb(EG(a),10);b.yb(pk(),ok());}}
function ok(){fk();return bj();}
function pk(){fk();return cj();}
function qk(){fk();return $doc.documentElement.scrollLeft||$doc.body.scrollLeft;}
function rk(){fk();return $doc.documentElement.scrollTop||$doc.body.scrollTop;}
function sk(){fk();__gwt_initHandlers(function(){vk();},function(){return uk();},function(){tk();$wnd.onresize=null;$wnd.onbeforeclose=null;$wnd.onclose=null;});}
function tk(){fk();var a;a=x;{lk();}}
function uk(){fk();var a;a=x;{return mk();}}
function vk(){fk();var a;a=x;{nk();}}
function xk(a){fk();$doc.body.style.margin=a;}
var jk,wk;function vl(c,b,a){b.appendChild(a);}
function xl(b,a){return $doc.createElement(a);}
function yl(b,c){var a=$doc.createElement('INPUT');a.type=c;return a;}
function zl(c,b,a){b.cancelBubble=a;}
function Al(b,a){return !(!a.altKey);}
function Bl(b,a){return !(!a.ctrlKey);}
function Cl(b,a){return a.which||(a.keyCode|| -1);}
function Dl(b,a){return !(!a.metaKey);}
function El(b,a){return !(!a.shiftKey);}
function Fl(b,a){switch(a.type){case 'blur':return 4096;case 'change':return 1024;case 'click':return 1;case 'dblclick':return 2;case 'focus':return 2048;case 'keydown':return 128;case 'keypress':return 256;case 'keyup':return 512;case 'load':return 32768;case 'losecapture':return 8192;case 'mousedown':return 4;case 'mousemove':return 64;case 'mouseout':return 32;case 'mouseover':return 16;case 'mouseup':return 8;case 'scroll':return 16384;case 'error':return 65536;case 'mousewheel':return 131072;case 'DOMMouseScroll':return 131072;}}
function am(c,b){var a=$doc.getElementById(b);return a||null;}
function dm(d,a,b){var c=a[b];return c==null?null:String(c);}
function bm(c,a,b){return !(!a[b]);}
function cm(d,a,c){var b=parseInt(a[c]);if(!b){return 0;}return b;}
function em(b,a){return a.__eventBits||0;}
function fm(c,b,a){b.removeChild(a);}
function gm(g,b){var d=b.offsetLeft,h=b.offsetTop;var i=b.offsetWidth,c=b.offsetHeight;if(b.parentNode!=b.offsetParent){d-=b.parentNode.offsetLeft;h-=b.parentNode.offsetTop;}var a=b.parentNode;while(a&&a.nodeType==1){if(a.style.overflow=='auto'||(a.style.overflow=='scroll'||a.tagName=='BODY')){if(d<a.scrollLeft){a.scrollLeft=d;}if(d+i>a.scrollLeft+a.clientWidth){a.scrollLeft=d+i-a.clientWidth;}if(h<a.scrollTop){a.scrollTop=h;}if(h+c>a.scrollTop+a.clientHeight){a.scrollTop=h+c-a.clientHeight;}}var e=a.offsetLeft,f=a.offsetTop;if(a.parentNode!=a.offsetParent){e-=a.parentNode.offsetLeft;f-=a.parentNode.offsetTop;}d+=e-a.scrollLeft;h+=f-a.scrollTop;a=a.parentNode;}}
function jm(c,a,b,d){a[b]=d;}
function hm(c,a,b,d){a[b]=d;}
function im(c,a,b,d){a[b]=d;}
function km(c,a,b){a.__listener=b;}
function lm(c,a,b){if(!b){b='';}a.innerHTML=b;}
function mm(c,b,a,d){b.style[a]=d;}
function nm(c,b,a,d){b.style[a]=d;}
function om(a){return $doc.body.clientHeight;}
function pm(a){return $doc.body.clientWidth;}
function yk(){}
_=yk.prototype=new sF();_.tN=pL+'DOMImpl';_.tI=46;function Bk(c,a,b){if(!a&& !b)return true;else if(!a|| !b)return false;return a.uniqueID==b.uniqueID;}
function Ck(b,a){return a.clientX-tl();}
function Dk(b,a){return a.clientY-ul();}
function Ek(b,a){return sl;}
function Fk(b,a){return a.fromElement?a.fromElement:null;}
function al(b,a){return a.srcElement||null;}
function bl(b,a){return a.toElement||null;}
function cl(b,a){a.returnValue=false;}
function dl(c,a){var b=$doc.documentElement.scrollLeft||$doc.body.scrollLeft;return a.getBoundingClientRect().left+b-tl();}
function el(c,a){var b=$doc.documentElement.scrollTop||$doc.body.scrollTop;return a.getBoundingClientRect().top+b-ul();}
function hl(d,b,c){var a=b.children[c];return a||null;}
function fl(b,a){return a.children.length;}
function gl(e,d,a){var b=d.children.length;for(var c=0;c<b;++c){if(a.uniqueID==d.children[c].uniqueID)return c;}return -1;}
function il(c,b){var a=b.firstChild;return a||null;}
function jl(c,a){var b=a.parentElement;return b||null;}
function kl(d){try{$doc.execCommand('BackgroundImageCache',false,true);}catch(a){}$wnd.__dispatchEvent=function(){var c=sl;sl=this;if($wnd.event.returnValue==null){$wnd.event.returnValue=true;if(!pi($wnd.event)){sl=c;return;}}var b,a=this;while(a&& !(b=a.__listener))a=a.parentElement;if(b)rh($wnd.event,a,b);sl=c;};$wnd.__dispatchDblClickEvent=function(){var a=$doc.createEventObject();this.fireEvent('onclick',a);if(this.__eventBits&2)$wnd.__dispatchEvent.call(this);};$doc.body.onclick=$doc.body.onmousedown=$doc.body.onmouseup=$doc.body.onmousemove=$doc.body.onmousewheel=$doc.body.onkeydown=$doc.body.onkeypress=$doc.body.onkeyup=$doc.body.onfocus=$doc.body.onblur=$doc.body.ondblclick=$wnd.__dispatchEvent;}
function ll(d,c,a,b){if(b>=c.children.length)c.appendChild(a);else c.insertBefore(a,c.children[b]);}
function ml(c,b,a){while(a){if(b.uniqueID==a.uniqueID)return true;a=a.parentElement;}return false;}
function nl(b,a){a.releaseCapture();}
function ol(b,a){a.setCapture();}
function pl(c,a,b){zm(a,b);}
function ql(c,a,b){if(!b)b='';a.innerText=b;}
function rl(c,b,a){b.__eventBits=a;b.onclick=a&1?$wnd.__dispatchEvent:null;b.ondblclick=a&(1|2)?$wnd.__dispatchDblClickEvent:null;b.onmousedown=a&4?$wnd.__dispatchEvent:null;b.onmouseup=a&8?$wnd.__dispatchEvent:null;b.onmouseover=a&16?$wnd.__dispatchEvent:null;b.onmouseout=a&32?$wnd.__dispatchEvent:null;b.onmousemove=a&64?$wnd.__dispatchEvent:null;b.onkeydown=a&128?$wnd.__dispatchEvent:null;b.onkeypress=a&256?$wnd.__dispatchEvent:null;b.onkeyup=a&512?$wnd.__dispatchEvent:null;b.onchange=a&1024?$wnd.__dispatchEvent:null;b.onfocus=a&2048?$wnd.__dispatchEvent:null;b.onblur=a&4096?$wnd.__dispatchEvent:null;b.onlosecapture=a&8192?$wnd.__dispatchEvent:null;b.onscroll=a&16384?$wnd.__dispatchEvent:null;b.onload=a&32768?$wnd.__dispatchEvent:null;b.onerror=a&65536?$wnd.__dispatchEvent:null;b.onmousewheel=a&131072?$wnd.__dispatchEvent:null;}
function tl(){return $doc.documentElement.clientLeft||$doc.body.clientLeft;}
function ul(){return $doc.documentElement.clientTop||$doc.body.clientTop;}
function zk(){}
_=zk.prototype=new yk();_.tN=pL+'DOMImplIE6';_.tI=47;var sl=null;function sm(b,a){b.__kids.push(a);a.__pendingSrc=b.__pendingSrc;}
function tm(k,i,j){i.src=j;if(i.complete){return;}i.__kids=[];i.__pendingSrc=j;k[j]=i;var g=i.onload,f=i.onerror,e=i.onabort;function h(c){var d=i.__kids;i.__cleanup();window.setTimeout(function(){for(var a=0;a<d.length;++a){var b=d[a];if(b.__pendingSrc==j){b.src=j;b.__pendingSrc=null;}}},0);c&&c.call(i);}
i.onload=function(){h(g);};i.onerror=function(){h(f);};i.onabort=function(){h(e);};i.__cleanup=function(){i.onload=g;i.onerror=f;i.onabort=e;i.__cleanup=i.__pendingSrc=i.__kids=null;delete k[j];};}
function um(a){return a.__pendingSrc||a.src;}
function vm(a){return a.__pendingSrc||null;}
function wm(b,a){return b[a]||null;}
function xm(e,b){var f=b.uniqueID;var d=e.__kids;for(var c=0,a=d.length;c<a;++c){if(d[c].uniqueID==f){d.splice(c,1);b.__pendingSrc=null;return;}}}
function ym(f,c){var e=c.__pendingSrc;var d=c.__kids;c.__cleanup();if(c=d[0]){c.__pendingSrc=null;tm(f,c,e);if(c.__pendingSrc){d.splice(0,1);c.__kids=d;}else{for(var b=1,a=d.length;b<a;++b){d[b].src=e;d[b].__pendingSrc=null;}}}}
function zm(a,c){var b,d;if(DF(um(a),c)){return;}if(Am===null){Am=fb();}b=vm(a);if(b!==null){d=wm(Am,b);if(jj(d,ac(a,hj))){ym(Am,d);}else{xm(d,a);}}d=wm(Am,c);if(d===null){tm(Am,a,c);}else{sm(d,a);}}
var Am=null;function Cm(a){lo(a);a.Eb(fh());Fi(a.D(),'position','relative');Fi(a.D(),'overflow','hidden');return a;}
function Dm(a,b){mo(a,b,a.D());}
function Fm(b,c){var a;a=to(b,c);if(a){an(c.D());}return a;}
function an(a){Fi(a,'left','');Fi(a,'top','');Fi(a,'position','');}
function bn(a){return Fm(this,a);}
function Bm(){}
_=Bm.prototype=new jo();_.Bb=bn;_.tN=qL+'AbsolutePanel';_.tI=48;function cn(){}
_=cn.prototype=new sF();_.tN=qL+'AbstractImagePrototype';_.tI=49;function Aq(){Aq=kL;wD(),zD;}
function yq(b,a){wD(),zD;Bq(b,a);return b;}
function zq(b,a){if(b.c===null){b.c=fo(new eo());}zI(b.c,a);}
function Bq(b,a){sC(b,a);EA(b,7041);}
function Cq(a){switch(Eh(a)){case 1:if(this.c!==null){ho(this.c,this);}break;case 4096:case 2048:break;case 128:case 512:case 256:break;}}
function Dq(a){Bq(this,a);}
function xq(){}
_=xq.prototype=new rB();_.jb=Cq;_.Eb=Dq;_.tN=qL+'FocusWidget';_.tI=50;_.c=null;function hn(){hn=kL;wD(),zD;}
function gn(b,a){wD(),zD;yq(b,a);return b;}
function jn(a){Ci(this.D(),a);}
function fn(){}
_=fn.prototype=new xq();_.Fb=jn;_.tN=qL+'ButtonBase';_.tI=51;function nn(){nn=kL;wD(),zD;}
function kn(a){wD(),zD;gn(a,eh());on(a.D());DA(a,'gwt-Button');return a;}
function ln(b,a){wD(),zD;kn(b);b.Fb(a);return b;}
function mn(c,a,b){wD(),zD;ln(c,a);zq(c,b);return c;}
function on(b){nn();if(b.type=='submit'){try{b.setAttribute('type','button');}catch(a){}}}
function en(){}
_=en.prototype=new fn();_.tN=qL+'Button';_.tI=52;function qn(a){lo(a);a.e=oh();a.d=lh();ch(a.e,a.d);a.Eb(a.e);return a;}
function sn(a,b){if(b.q!==a){return null;}return li(b.D());}
function tn(c,b,a){zi(b,'align',a.a);}
function un(c,b,a){Fi(b,'verticalAlign',a.a);}
function vn(b,a){yi(b.e,'cellSpacing',a);}
function wn(c,a){var b;b=sn(this,c);if(b!==null){tn(this,b,a);}}
function pn(){}
_=pn.prototype=new jo();_.Db=wn;_.tN=qL+'CellPanel';_.tI=53;_.d=null;_.e=null;function Bn(){Bn=kL;wD(),zD;}
function yn(a){wD(),zD;zn(a,ih());DA(a,'gwt-CheckBox');return a;}
function An(b,a){wD(),zD;yn(b);En(b,a);return b;}
function zn(b,a){var c;wD(),zD;gn(b,kh());b.a=a;b.b=jh();aj(b.a,ji(b.D()));aj(b.D(),0);ch(b.D(),b.a);ch(b.D(),b.b);c='check'+ ++co;zi(b.a,'id',c);zi(b.b,'htmlFor',c);return b;}
function Cn(b){var a;a=b.eb()?'checked':'defaultChecked';return gi(b.a,a);}
function Dn(b,a){xi(b.a,'checked',a);xi(b.a,'defaultChecked',a);}
function En(b,a){Di(b.b,a);}
function Fn(){Ai(this.a,this);}
function ao(){Ai(this.a,null);Dn(this,Cn(this));}
function bo(a){Ci(this.b,a);}
function xn(){}
_=xn.prototype=new fn();_.pb=Fn;_.vb=ao;_.Fb=bo;_.tN=qL+'CheckBox';_.tI=54;_.a=null;_.b=null;var co=0;function uG(d,a,b){var c;while(a.db()){c=a.hb();if(b===null?c===null:b.eQ(c)){return a;}}return null;}
function wG(a){throw rG(new qG(),'add');}
function xG(b){var a;a=uG(this,this.fb(),b);return a!==null;}
function yG(a){var b,c,d;d=this.fc();if(a.a<d){a=ob(a,d);}b=0;for(c=this.fb();c.db();){vb(a,b++,c.hb());}if(a.a>d){vb(a,d,null);}return a;}
function tG(){}
_=tG.prototype=new sF();_.u=wG;_.w=xG;_.gc=yG;_.tN=tL+'AbstractCollection';_.tI=55;function dH(b,a){throw xE(new wE(),'Index: '+a+', Size: '+b.b);}
function eH(a){return BG(new AG(),a);}
function fH(b,a){throw rG(new qG(),'add');}
function gH(a){this.t(this.fc(),a);return true;}
function hH(e){var a,b,c,d,f;if(e===this){return true;}if(!Ab(e,25)){return false;}f=zb(e,25);if(this.fc()!=f.fc()){return false;}c=eH(this);d=f.fb();while(DG(c)){a=EG(c);b=EG(d);if(!(a===null?b===null:a.eQ(b))){return false;}}return true;}
function iH(){var a,b,c,d;c=1;a=31;b=eH(this);while(DG(b)){d=EG(b);c=31*c+(d===null?0:d.hC());}return c;}
function jH(){return eH(this);}
function kH(a){throw rG(new qG(),'remove');}
function zG(){}
_=zG.prototype=new tG();_.t=fH;_.u=gH;_.eQ=hH;_.hC=iH;_.fb=jH;_.Ab=kH;_.tN=tL+'AbstractList';_.tI=56;function xI(a){{AI(a);}}
function yI(a){xI(a);return a;}
function zI(b,a){nJ(b.a,b.b++,a);return true;}
function AI(a){a.a=eb();a.b=0;}
function CI(b,a){return EI(b,a)!=(-1);}
function DI(b,a){if(a<0||a>=b.b){dH(b,a);}return jJ(b.a,a);}
function EI(b,a){return FI(b,a,0);}
function FI(c,b,a){if(a<0){dH(c,a);}for(;a<c.b;++a){if(iJ(b,jJ(c.a,a))){return a;}}return (-1);}
function aJ(a){return a.b==0;}
function bJ(c,a){var b;b=DI(c,a);lJ(c.a,a,1);--c.b;return b;}
function cJ(c,b){var a;a=EI(c,b);if(a==(-1)){return false;}bJ(c,a);return true;}
function dJ(d,a,b){var c;c=DI(d,a);nJ(d.a,a,b);return c;}
function fJ(a,b){if(a<0||a>this.b){dH(this,a);}eJ(this.a,a,b);++this.b;}
function gJ(a){return zI(this,a);}
function eJ(a,b,c){a.splice(b,0,c);}
function hJ(a){return CI(this,a);}
function iJ(a,b){return a===b||a!==null&&a.eQ(b);}
function kJ(a){return DI(this,a);}
function jJ(a,b){return a[b];}
function mJ(a){return bJ(this,a);}
function lJ(a,c,b){a.splice(c,b);}
function nJ(a,b,c){a[b]=c;}
function oJ(){return this.b;}
function pJ(a){var b;if(a.a<this.b){a=ob(a,this.b);}for(b=0;b<this.b;++b){vb(a,b,jJ(this.a,b));}if(a.a>this.b){vb(a,this.b,null);}return a;}
function wI(){}
_=wI.prototype=new zG();_.t=fJ;_.u=gJ;_.w=hJ;_.bb=kJ;_.Ab=mJ;_.fc=oJ;_.gc=pJ;_.tN=tL+'ArrayList';_.tI=57;_.a=null;_.b=0;function fo(a){yI(a);return a;}
function ho(d,c){var a,b;for(a=eH(d);DG(a);){b=zb(EG(a),11);b.lb(c);}}
function eo(){}
_=eo.prototype=new wI();_.tN=qL+'ClickListenerCollection';_.tI=58;function Ap(){Ap=kL;aq=new qp();bq=new qp();cq=new qp();dq=new qp();eq=new qp();}
function xp(a){a.b=(vt(),xt);a.c=(Et(),au);}
function yp(a){Ap();qn(a);xp(a);yi(a.e,'cellSpacing',0);yi(a.e,'cellPadding',0);return a;}
function zp(c,d,a){var b;if(a===aq){if(d===c.a){return;}else if(c.a!==null){throw rE(new qE(),'Only one CENTER widget may be added');}}rC(d);BB(c.f,d);if(a===aq){c.a=d;}b=tp(new sp(),a);tC(d,b);Dp(c,d,c.b);Ep(c,d,c.c);Bp(c);Av(c,d);}
function Bp(p){var a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,q;a=p.d;while(ci(a)>0){ri(a,ei(a,0));}l=1;d=1;for(h=aC(p.f);wB(h);){c=xB(h);e=c.p.a;if(e===cq||e===dq){++l;}else if(e===bq||e===eq){++d;}}m=tb('[Lcom.google.gwt.user.client.ui.DockPanel$TmpRow;',[125],[21],[l],null);for(g=0;g<l;++g){m[g]=new vp();m[g].b=nh();ch(a,m[g].b);}q=0;f=d-1;j=0;n=l-1;b=null;for(h=aC(p.f);wB(h);){c=xB(h);i=c.p;o=mh();i.d=o;zi(i.d,'align',i.b);Fi(i.d,'verticalAlign',i.e);zi(i.d,'width',i.f);zi(i.d,'height',i.c);if(i.a===cq){ni(m[j].b,o,m[j].a);ch(o,c.D());yi(o,'colSpan',f-q+1);++j;}else if(i.a===dq){ni(m[n].b,o,m[n].a);ch(o,c.D());yi(o,'colSpan',f-q+1);--n;}else if(i.a===eq){k=m[j];ni(k.b,o,k.a++);ch(o,c.D());yi(o,'rowSpan',n-j+1);++q;}else if(i.a===bq){k=m[j];ni(k.b,o,k.a);ch(o,c.D());yi(o,'rowSpan',n-j+1);--f;}else if(i.a===aq){b=o;}}if(p.a!==null){k=m[j];ni(k.b,b,k.a);ch(b,p.a.D());}}
function Cp(c,d,b){var a;a=d.p;a.c=b;if(a.d!==null){Fi(a.d,'height',a.c);}}
function Dp(c,d,a){var b;b=d.p;b.b=a.a;if(b.d!==null){zi(b.d,'align',b.b);}}
function Ep(c,d,a){var b;b=d.p;b.e=a.a;if(b.d!==null){Fi(b.d,'verticalAlign',b.e);}}
function Fp(b,c,d){var a;a=c.p;a.f=d;if(a.d!==null){Fi(a.d,'width',a.f);}}
function fq(b){var a;a=to(this,b);if(a){if(b===this.a){this.a=null;}Bp(this);}return a;}
function gq(b,a){Dp(this,b,a);}
function pp(){}
_=pp.prototype=new pn();_.Bb=fq;_.Db=gq;_.tN=qL+'DockPanel';_.tI=59;_.a=null;var aq,bq,cq,dq,eq;function qp(){}
_=qp.prototype=new sF();_.tN=qL+'DockPanel$DockLayoutConstant';_.tI=60;function tp(b,a){b.a=a;return b;}
function sp(){}
_=sp.prototype=new sF();_.tN=qL+'DockPanel$LayoutData';_.tI=61;_.a=null;_.b='left';_.c='';_.d=null;_.e='top';_.f='';function vp(){}
_=vp.prototype=new sF();_.tN=qL+'DockPanel$TmpRow';_.tI=62;_.a=0;_.b=null;function qs(a){a.g=gs(new bs());}
function rs(a){qs(a);a.e=oh();a.a=lh();ch(a.e,a.a);a.Eb(a.e);EA(a,1);return a;}
function ss(b,a){if(b.f===null){b.f=zy(new yy());}zI(b.f,a);}
function ts(d,c,b){var a;us(d,c);if(b<0){throw xE(new wE(),'Column '+b+' must be non-negative: '+b);}a=oq(d,c);if(a<=b){throw xE(new wE(),'Column index: '+b+', Column size: '+oq(d,c));}}
function us(c,a){var b;b=pq(c);if(a>=b||a<0){throw xE(new wE(),'Row index: '+a+', Row size: '+b);}}
function vs(e,c,b,a){var d;d=or(e.b,c,b);Cs(e,d,a);return d;}
function xs(c,b,a){return b.rows[a].cells.length;}
function ys(a){return zs(a,a.a);}
function zs(b,a){return a.rows.length;}
function As(d,b){var a,c,e;c=Ch(b);for(;c!==null;c=li(c)){if(CF(ii(c,'tagName'),'td')){e=li(c);a=li(e);if(dh(a,d.a)){return c;}}if(dh(c,d.a)){return null;}}return null;}
function Bs(b,a){var c;if(a!=pq(b)){us(b,a);}c=nh();ni(b.a,c,a);return a;}
function Cs(d,c,a){var b,e;b=ki(c);e=null;if(b!==null){e=is(d.g,b);}if(e!==null){Ds(d,e);return true;}else{if(a){Ci(c,'');}return false;}}
function Ds(b,c){var a;if(c.q!==b){return false;}Cv(b,c);a=c.D();ri(li(a),a);ls(b.g,a);return true;}
function Es(a,b){zi(a.e,'border',''+b);}
function Fs(b,a){b.b=a;}
function at(b,a){yi(b.e,'cellPadding',a);}
function bt(b,a){yi(b.e,'cellSpacing',a);}
function ct(b,a){b.c=a;yr(b.c);}
function dt(e,c,a,b){var d;rq(e,c,a);d=vs(e,c,a,b===null);if(b!==null){Ci(d,b);}}
function et(b,a){b.d=a;}
function ft(e,b,a,d){var c;rq(e,b,a);c=vs(e,b,a,d===null);if(d!==null){Di(c,d);}}
function gt(d,b,a,e){var c;rq(d,b,a);if(e!==null){rC(e);c=vs(d,b,a,true);js(d.g,e);ch(c,e.D());Av(d,e);}}
function ht(){return ms(this.g);}
function it(c){var a,b,d,e,f;switch(Eh(c)){case 1:{if(this.f!==null){e=As(this,c);if(e===null){return;}f=li(e);a=li(f);d=di(a,f);b=di(f,e);By(this.f,this,d,b);}break;}default:}}
function jt(a){return Ds(this,a);}
function Fq(){}
_=Fq.prototype=new zv();_.fb=ht;_.jb=it;_.Bb=jt;_.tN=qL+'HTMLTable';_.tI=63;_.a=null;_.b=null;_.c=null;_.d=null;_.e=null;_.f=null;function mq(a){rs(a);Fs(a,jq(new iq(),a));et(a,Ar(new zr(),a));ct(a,wr(new vr(),a));return a;}
function oq(b,a){us(b,a);return xs(b,b.a,a);}
function pq(a){return ys(a);}
function qq(b,a){return Bs(b,a);}
function rq(e,d,b){var a,c;sq(e,d);if(b<0){throw xE(new wE(),'Cannot create a column with a negative index: '+b);}a=oq(e,d);c=b+1-a;if(c>0){tq(e.a,d,c);}}
function sq(d,b){var a,c;if(b<0){throw xE(new wE(),'Cannot create a row with a negative index: '+b);}c=pq(d);for(a=c;a<=b;a++){qq(d,a);}}
function tq(f,d,c){var e=f.rows[d];for(var b=0;b<c;b++){var a=$doc.createElement('td');e.appendChild(a);}}
function hq(){}
_=hq.prototype=new Fq();_.tN=qL+'FlexTable';_.tI=64;function jr(b,a){b.a=a;return b;}
function lr(c,b,a){rq(c.a,b,a);return mr(c,c.a.a,b,a);}
function mr(e,d,c,a){var b=d.rows[c].cells[a];return b==null?null:b;}
function nr(c,b,a){ts(c.a,b,a);return mr(c,c.a.a,b,a);}
function or(c,b,a){return mr(c,c.a.a,b,a);}
function pr(d,c,a,b,e){rr(d,c,a,b);sr(d,c,a,e);}
function qr(e,d,a,c){var b;rq(e.a,d,a);b=mr(e,e.a.a,d,a);zi(b,'height',c);}
function rr(e,d,b,a){var c;rq(e.a,d,b);c=mr(e,e.a.a,d,b);zi(c,'align',a.a);}
function sr(d,c,b,a){rq(d.a,c,b);Fi(mr(d,d.a.a,c,b),'verticalAlign',a.a);}
function tr(c,b,a,d){rq(c.a,b,a);zi(mr(c,c.a.a,b,a),'width',d);}
function ur(c,b,a,d){var e;rq(c.a,b,a);e=d?'':'nowrap';Fi(nr(c,b,a),'whiteSpace',e);}
function ir(){}
_=ir.prototype=new sF();_.tN=qL+'HTMLTable$CellFormatter';_.tI=65;function jq(b,a){jr(b,a);return b;}
function lq(d,c,b,a){yi(lr(d,c,b),'colSpan',a);}
function iq(){}
_=iq.prototype=new ir();_.tN=qL+'FlexTable$FlexCellFormatter';_.tI=66;function vq(){vq=kL;wq=(wD(),yD);}
var wq;function hv(a){a.Eb(fh());EA(a,131197);DA(a,'gwt-Label');return a;}
function iv(b,a){hv(b);mv(b,a);return b;}
function jv(b,a){if(b.a===null){b.a=fo(new eo());}zI(b.a,a);}
function kv(b,a){if(b.b===null){b.b=qv(new pv());}zI(b.b,a);}
function mv(b,a){Di(b.D(),a);}
function nv(a,b){Fi(a.D(),'whiteSpace',b?'normal':'nowrap');}
function ov(a){switch(Eh(a)){case 1:if(this.a!==null){ho(this.a,this);}break;case 4:case 8:case 64:case 16:case 32:if(this.b!==null){uv(this.b,this,a);}break;case 131072:break;}}
function gv(){}
_=gv.prototype=new rB();_.jb=ov;_.tN=qL+'Label';_.tI=67;_.a=null;_.b=null;function kt(a){hv(a);a.Eb(fh());EA(a,125);DA(a,'gwt-HTML');return a;}
function lt(b,a){kt(b);ot(b,a);return b;}
function mt(b,a,c){lt(b,a);nv(b,c);return b;}
function ot(b,a){Ci(b.D(),a);}
function Eq(){}
_=Eq.prototype=new gv();_.tN=qL+'HTML';_.tI=68;function br(a){{er(a);}}
function cr(b,a){b.b=a;br(b);return b;}
function er(a){while(++a.a<a.b.b.b){if(DI(a.b.b,a.a)!==null){return;}}}
function fr(a){return a.a<a.b.b.b;}
function gr(){return fr(this);}
function hr(){var a;if(!fr(this)){throw new gL();}a=DI(this.b.b,this.a);er(this);return a;}
function ar(){}
_=ar.prototype=new sF();_.db=gr;_.hb=hr;_.tN=qL+'HTMLTable$1';_.tI=69;_.a=(-1);function wr(b,a){b.b=a;return b;}
function yr(a){if(a.a===null){a.a=gh('colgroup');ni(a.b.e,a.a,0);ch(a.a,gh('col'));}}
function vr(){}
_=vr.prototype=new sF();_.tN=qL+'HTMLTable$ColumnFormatter';_.tI=70;_.a=null;function Ar(b,a){b.a=a;return b;}
function Br(c,a,b){fB(Dr(c,a),b,true);}
function Dr(b,a){sq(b.a,a);return Er(b,b.a.a,a);}
function Er(c,a,b){return a.rows[b];}
function Fr(c,a,b){fB(Dr(c,a),b,false);}
function as(c,a,b){eB(Dr(c,a),b);}
function zr(){}
_=zr.prototype=new sF();_.tN=qL+'HTMLTable$RowFormatter';_.tI=71;function fs(a){a.b=yI(new wI());}
function gs(a){fs(a);return a;}
function is(c,a){var b;b=os(a);if(b<0){return null;}return zb(DI(c.b,b),12);}
function js(b,c){var a;if(b.a===null){a=b.b.b;zI(b.b,c);}else{a=b.a.a;dJ(b.b,a,c);b.a=b.a.b;}ps(c.D(),a);}
function ks(c,a,b){ns(a);dJ(c.b,b,null);c.a=ds(new cs(),b,c.a);}
function ls(c,a){var b;b=os(a);ks(c,a,b);}
function ms(a){return cr(new ar(),a);}
function ns(a){a['__widgetID']=null;}
function os(a){var b=a['__widgetID'];return b==null?-1:b;}
function ps(a,b){a['__widgetID']=b;}
function bs(){}
_=bs.prototype=new sF();_.tN=qL+'HTMLTable$WidgetMapper';_.tI=72;_.a=null;function ds(c,a,b){c.a=a;c.b=b;return c;}
function cs(){}
_=cs.prototype=new sF();_.tN=qL+'HTMLTable$WidgetMapper$FreeNode';_.tI=73;_.a=0;_.b=null;function vt(){vt=kL;wt=tt(new st(),'center');xt=tt(new st(),'left');yt=tt(new st(),'right');}
var wt,xt,yt;function tt(b,a){b.a=a;return b;}
function st(){}
_=st.prototype=new sF();_.tN=qL+'HasHorizontalAlignment$HorizontalAlignmentConstant';_.tI=74;_.a=null;function Et(){Et=kL;Ct(new Bt(),'bottom');Ft=Ct(new Bt(),'middle');au=Ct(new Bt(),'top');}
var Ft,au;function Ct(a,b){a.a=b;return a;}
function Bt(){}
_=Bt.prototype=new sF();_.tN=qL+'HasVerticalAlignment$VerticalAlignmentConstant';_.tI=75;_.a=null;function eu(a){a.a=(vt(),xt);a.c=(Et(),au);}
function fu(a){qn(a);eu(a);a.b=nh();ch(a.d,a.b);zi(a.e,'cellSpacing','0');zi(a.e,'cellPadding','0');return a;}
function gu(b,c){var a;a=iu(b);ch(b.b,a);mo(b,c,a);}
function iu(b){var a;a=mh();tn(b,a,b.a);un(b,a,b.c);return a;}
function ju(b,a){b.a=a;}
function ku(c){var a,b;b=li(c.D());a=to(this,c);if(a){ri(this.b,b);}return a;}
function du(){}
_=du.prototype=new pn();_.Bb=ku;_.tN=qL+'HorizontalPanel';_.tI=76;_.b=null;function Fu(){Fu=kL;lK(new sJ());}
function Cu(a){Fu();Eu(a,yu(new xu(),a));DA(a,'gwt-Image');return a;}
function Du(c,e,b,d,f,a){Fu();Eu(c,qu(new pu(),c,e,b,d,f,a));DA(c,'gwt-Image');return c;}
function Eu(b,a){b.a=a;}
function av(c,e,b,d,f,a){c.a.bc(c,e,b,d,f,a);}
function bv(a){switch(Eh(a)){case 1:{break;}case 4:case 8:case 64:case 16:case 32:{break;}case 131072:break;case 32768:{break;}case 65536:{break;}}}
function lu(){}
_=lu.prototype=new rB();_.jb=bv;_.tN=qL+'Image';_.tI=77;_.a=null;function ou(){}
function mu(){}
_=mu.prototype=new sF();_.A=ou;_.tN=qL+'Image$1';_.tI=78;function vu(){}
_=vu.prototype=new sF();_.tN=qL+'Image$State';_.tI=79;function ru(){ru=kL;tu=aD(new FC());}
function qu(d,b,f,c,e,g,a){ru();d.b=c;d.c=e;d.e=g;d.a=a;d.d=f;b.Eb(hD(tu,f,c,e,g,a));EA(b,131197);su(d,b);return d;}
function su(b,a){fj(new mu());}
function uu(b,e,c,d,f,a){if(!DF(this.d,e)||this.b!=c||this.c!=d||this.e!=f||this.a!=a){this.d=e;this.b=c;this.c=d;this.e=f;this.a=a;bD(tu,b.D(),e,c,d,f,a);su(this,b);}}
function pu(){}
_=pu.prototype=new vu();_.bc=uu;_.tN=qL+'Image$ClippedState';_.tI=80;_.a=0;_.b=0;_.c=0;_.d=null;_.e=0;var tu;function yu(b,a){a.Eb(hh());EA(a,229501);return b;}
function Au(b,e,c,d,f,a){Eu(b,qu(new pu(),b,e,c,d,f,a));}
function xu(){}
_=xu.prototype=new vu();_.bc=Au;_.tN=qL+'Image$UnclippedState';_.tI=81;function fv(a){return (Bh(a)?1:0)|(Ah(a)?8:0)|(wh(a)?2:0)|(th(a)?4:0);}
function qv(a){yI(a);return a;}
function sv(d,c,e,f){var a,b;for(a=eH(d);DG(a);){b=zb(EG(a),13);b.qb(c,e,f);}}
function tv(d,c){var a,b;for(a=eH(d);DG(a);){b=zb(EG(a),13);b.rb(c);}}
function uv(e,c,a){var b,d,f,g,h;d=c.D();g=uh(a)-ai(d)+hi(d,'scrollLeft')+qk();h=vh(a)-bi(d)+hi(d,'scrollTop')+rk();switch(Eh(a)){case 4:sv(e,c,g,h);break;case 8:xv(e,c,g,h);break;case 64:wv(e,c,g,h);break;case 16:b=yh(a);if(!oi(d,b)){tv(e,c);}break;case 32:f=Dh(a);if(!oi(d,f)){vv(e,c);}break;}}
function vv(d,c){var a,b;for(a=eH(d);DG(a);){b=zb(EG(a),13);b.sb(c);}}
function wv(d,c,e,f){var a,b;for(a=eH(d);DG(a);){b=zb(EG(a),13);b.tb(c,e,f);}}
function xv(d,c,e,f){var a,b;for(a=eH(d);DG(a);){b=zb(EG(a),13);b.ub(c,e,f);}}
function pv(){}
_=pv.prototype=new wI();_.tN=qL+'MouseListenerCollection';_.tI=82;function dx(){dx=kL;ix=lK(new sJ());}
function cx(b,a){dx();Cm(b);if(a===null){a=ex();}b.Eb(a);b.ib();return b;}
function fx(){dx();return gx(null);}
function gx(c){dx();var a,b;b=zb(rK(ix,c),14);if(b!==null){return b;}a=null;if(ix.c==0){hx();}sK(ix,c,b=cx(new Dw(),a));return b;}
function ex(){dx();return $doc.body;}
function hx(){dx();gk(new Ew());}
function Dw(){}
_=Dw.prototype=new Bm();_.tN=qL+'RootPanel';_.tI=83;var ix;function ax(){var a,b;for(b=DH(lI((dx(),ix)));eI(b);){a=zb(fI(b),14);if(a.eb()){a.mb();}}}
function bx(){return null;}
function Ew(){}
_=Ew.prototype=new sF();_.wb=ax;_.xb=bx;_.tN=qL+'RootPanel$1';_.tI=84;function kx(a){wx(a);nx(a,false);EA(a,16384);return a;}
function lx(b,a){kx(b);b.dc(a);return b;}
function nx(b,a){Fi(b.D(),'overflow',a?'scroll':'auto');}
function ox(a){Eh(a)==16384;}
function jx(){}
_=jx.prototype=new px();_.jb=ox;_.tN=qL+'ScrollPanel';_.tI=85;function rx(a){a.a=a.b.n!==null;}
function sx(b,a){b.b=a;rx(b);return b;}
function ux(){return this.a;}
function vx(){if(!this.a||this.b.n===null){throw new gL();}this.a=false;return this.b.n;}
function qx(){}
_=qx.prototype=new sF();_.db=ux;_.hb=vx;_.tN=qL+'SimplePanel$1';_.tI=86;function zy(a){yI(a);return a;}
function By(f,e,d,a){var b,c;for(b=eH(f);DG(b);){c=zb(EG(b),15);c.kb(e,d,a);}}
function yy(){}
_=yy.prototype=new wI();_.tN=qL+'TableListenerCollection';_.tI=87;function Bz(a){a.a=lK(new sJ());}
function Cz(b,a){Bz(b);b.d=a;b.Eb(fh());Fi(b.D(),'position','relative');b.c=xD((vq(),wq));Fi(b.c,'fontSize','0');Fi(b.c,'position','absolute');Ei(b.c,'zIndex',(-1));ch(b.D(),b.c);EA(b,1021);aj(b.c,6144);b.f=Fy(new Ey(),b);vz(b.f,b);DA(b,'gwt-Tree');return b;}
function Dz(b,a){az(b.f,a);}
function Fz(d,a,c,b){if(b===null||dh(b,c)){return;}Fz(d,a,c,li(b));zI(a,ac(b,hj));}
function aA(e,d,b){var a,c;a=yI(new wI());Fz(e,a,e.D(),b);c=cA(e,a,0,d);if(c!==null){if(oi(oz(c),b)){uz(c,!c.f,true);return true;}else if(oi(c.D(),b)){hA(e,c,true,!mA(e,b));return true;}}return false;}
function bA(b,a){if(!a.f){return a;}return bA(b,mz(a,a.c.b-1));}
function cA(i,a,e,h){var b,c,d,f,g;if(e==a.b){return h;}c=zb(DI(a,e),7);for(d=0,f=h.c.b;d<f;++d){b=mz(h,d);if(dh(b.D(),c)){g=cA(i,a,e+1,mz(h,d));if(g===null){return b;}return g;}}return cA(i,a,e+1,h);}
function dA(a){var b;b=tb('[Lcom.google.gwt.user.client.ui.Widget;',[128],[12],[a.a.c],null);kI(a.a).gc(b);return oC(a,b);}
function eA(h,g){var a,b,c,d,e,f,i,j;c=nz(g);{f=g.d;a=wA(h);b=xA(h);e=ai(f)-a;i=bi(f)-b;j=hi(f,'offsetWidth');d=hi(f,'offsetHeight');Ei(h.c,'left',e);Ei(h.c,'top',i);Ei(h.c,'width',j);Ei(h.c,'height',d);vi(h.c);tD((vq(),wq),h.c);}}
function fA(e,d,a){var b,c;if(d===e.f){return;}c=d.g;if(c===null){c=e.f;}b=lz(c,d);if(!a|| !d.f){if(b<c.c.b-1){hA(e,mz(c,b+1),true,true);}else{fA(e,c,false);}}else if(d.c.b>0){hA(e,mz(d,0),true,true);}}
function gA(e,c){var a,b,d;b=c.g;if(b===null){b=e.f;}a=lz(b,c);if(a>0){d=mz(b,a-1);hA(e,bA(e,d),true,true);}else{hA(e,b,true,true);}}
function hA(d,b,a,c){if(b===d.f){return;}if(d.b!==null){sz(d.b,false);}d.b=b;if(c&&d.b!==null){eA(d,d.b);sz(d.b,true);}}
function iA(b,a){cz(b.f,a);}
function jA(b,a){if(a){tD((vq(),wq),b.c);}else{vD((vq(),wq),b.c);}}
function kA(b,a){lA(b,a,true);}
function lA(c,b,a){if(b===null){if(c.b===null){return;}sz(c.b,false);c.b=null;return;}hA(c,b,a,true);}
function mA(c,a){var b=a.nodeName;return b=='SELECT'||(b=='INPUT'||(b=='TEXTAREA'||(b=='OPTION'||(b=='BUTTON'||b=='LABEL'))));}
function nA(){var a,b;for(b=dA(this);jC(b);){a=kC(b);a.ib();}Ai(this.c,this);}
function oA(){var a,b;for(b=dA(this);jC(b);){a=kC(b);a.mb();}Ai(this.c,null);}
function pA(){return dA(this);}
function qA(c){var a,b,d,e,f;d=Eh(c);switch(d){case 1:{b=Ch(c);if(mA(this,b)){}else{jA(this,true);}break;}case 4:{if(jj(xh(c),ac(this.D(),hj))){aA(this,this.f,Ch(c));}break;}case 8:{break;}case 64:{break;}case 16:{break;}case 32:{break;}case 2048:break;case 4096:{break;}case 128:if(this.b===null){if(this.f.c.b>0){hA(this,mz(this.f,0),true,true);}return;}if(this.e==128){return;}{switch(zh(c)){case 38:{gA(this,this.b);Fh(c);break;}case 40:{fA(this,this.b,true);Fh(c);break;}case 37:{if(this.b.f){tz(this.b,false);}else{f=this.b.g;if(f!==null){kA(this,f);}}Fh(c);break;}case 39:{if(!this.b.f){tz(this.b,true);}else if(this.b.c.b>0){kA(this,mz(this.b,0));}Fh(c);break;}}}case 512:if(d==512){if(zh(c)==9){a=yI(new wI());Fz(this,a,this.D(),Ch(c));e=cA(this,a,0,this.f);if(e!==this.b){lA(this,e,true);}}}case 256:{break;}}this.e=d;}
function rA(){xz(this.f);}
function sA(b){var a;a=zb(rK(this.a,b),16);if(a===null){return false;}wz(a,null);return true;}
function Dy(){}
_=Dy.prototype=new rB();_.x=nA;_.y=oA;_.fb=pA;_.jb=qA;_.pb=rA;_.Bb=sA;_.tN=qL+'Tree';_.tI=88;_.b=null;_.c=null;_.d=null;_.e=0;_.f=null;function hz(a){a.c=yI(new wI());a.i=Cu(new lu());}
function iz(d){var a,b,c,e;hz(d);d.Eb(fh());d.e=oh();d.d=kh();d.b=kh();a=lh();e=nh();c=mh();b=mh();ch(d.e,a);ch(a,e);ch(e,c);ch(e,b);Fi(c,'verticalAlign','middle');Fi(b,'verticalAlign','middle');ch(d.D(),d.e);ch(d.D(),d.b);ch(c,d.i.D());ch(b,d.d);Fi(d.d,'display','inline');Fi(d.D(),'whiteSpace','nowrap');Fi(d.b,'whiteSpace','nowrap');fB(d.d,'gwt-TreeItem',true);return d;}
function jz(b,a){iz(b);qz(b,a);return b;}
function mz(b,a){if(a<0||a>=b.c.b){return null;}return zb(DI(b.c,a),16);}
function lz(b,a){return EI(b.c,a);}
function nz(a){var b;b=a.k;{return null;}}
function oz(a){return a.i.D();}
function pz(a){if(a.g!==null){a.g.zb(a);}else if(a.j!==null){iA(a.j,a);}}
function qz(b,a){wz(b,null);Ci(b.d,a);}
function rz(b,a){b.g=a;}
function sz(b,a){if(b.h==a){return;}b.h=a;fB(b.d,'gwt-TreeItem-selected',a);}
function tz(b,a){uz(b,a,true);}
function uz(c,b,a){if(b&&c.c.b==0){return;}c.f=b;yz(c);}
function vz(d,c){var a,b;if(d.j===c){return;}if(d.j!==null){if(d.j.b===d){kA(d.j,null);}}d.j=c;for(a=0,b=d.c.b;a<b;++a){vz(zb(DI(d.c,a),16),c);}yz(d);}
function wz(b,a){Ci(b.d,'');b.k=a;}
function yz(b){var a;if(b.j===null){return;}a=b.j.d;if(b.c.b==0){gB(b.b,false);kD((ke(),Ae),b.i);return;}if(b.f){gB(b.b,true);kD((ke(),Be),b.i);}else{gB(b.b,false);kD((ke(),ze),b.i);}}
function xz(c){var a,b;yz(c);for(a=0,b=c.c.b;a<b;++a){xz(zb(DI(c.c,a),16));}}
function zz(a){if(a.g!==null||a.j!==null){pz(a);}rz(a,this);zI(this.c,a);Fi(a.D(),'marginLeft','16px');ch(this.b,a.D());vz(a,this.j);if(this.c.b==1){yz(this);}}
function Az(a){if(!CI(this.c,a)){return;}vz(a,null);ri(this.b,a.D());rz(a,null);cJ(this.c,a);if(this.c.b==0){yz(this);}}
function gz(){}
_=gz.prototype=new tA();_.s=zz;_.zb=Az;_.tN=qL+'TreeItem';_.tI=89;_.b=null;_.d=null;_.e=null;_.f=false;_.g=null;_.h=false;_.j=null;_.k=null;function Fy(b,a){b.a=a;iz(b);return b;}
function az(b,a){if(a.g!==null||a.j!==null){pz(a);}ch(b.a.D(),a.D());vz(a,b.j);rz(a,null);zI(b.c,a);Ei(a.D(),'marginLeft',0);}
function cz(b,a){if(!CI(b.c,a)){return;}vz(a,null);rz(a,null);cJ(b.c,a);ri(b.a.D(),a.D());}
function dz(a){az(this,a);}
function ez(a){cz(this,a);}
function Ey(){}
_=Ey.prototype=new gz();_.s=dz;_.zb=ez;_.tN=qL+'Tree$1';_.tI=90;function kB(a){a.a=(vt(),xt);a.b=(Et(),au);}
function lB(a){qn(a);kB(a);zi(a.e,'cellSpacing','0');zi(a.e,'cellPadding','0');return a;}
function mB(b,d){var a,c;c=nh();a=oB(b);ch(c,a);ch(b.d,c);mo(b,d,a);}
function oB(b){var a;a=mh();tn(b,a,b.a);un(b,a,b.b);return a;}
function pB(b,a){b.a=a;}
function qB(c){var a,b;b=li(c.D());a=to(this,c);if(a){ri(this.d,li(b));}return a;}
function jB(){}
_=jB.prototype=new pn();_.Bb=qB;_.tN=qL+'VerticalPanel';_.tI=91;function AB(b,a){b.a=tb('[Lcom.google.gwt.user.client.ui.Widget;',[128],[12],[4],null);return b;}
function BB(a,b){FB(a,b,a.b);}
function DB(b,a){if(a<0||a>=b.b){throw new wE();}return b.a[a];}
function EB(b,c){var a;for(a=0;a<b.b;++a){if(b.a[a]===c){return a;}}return (-1);}
function FB(d,e,a){var b,c;if(a<0||a>d.b){throw new wE();}if(d.b==d.a.a){c=tb('[Lcom.google.gwt.user.client.ui.Widget;',[128],[12],[d.a.a*2],null);for(b=0;b<d.a.a;++b){vb(c,b,d.a[b]);}d.a=c;}++d.b;for(b=d.b-1;b>a;--b){vb(d.a,b,d.a[b-1]);}vb(d.a,a,e);}
function aC(a){return uB(new tB(),a);}
function bC(c,b){var a;if(b<0||b>=c.b){throw new wE();}--c.b;for(a=b;a<c.b;++a){vb(c.a,a,c.a[a+1]);}vb(c.a,c.b,null);}
function cC(b,c){var a;a=EB(b,c);if(a==(-1)){throw new gL();}bC(b,a);}
function sB(){}
_=sB.prototype=new sF();_.tN=qL+'WidgetCollection';_.tI=92;_.a=null;_.b=0;function uB(b,a){b.b=a;return b;}
function wB(a){return a.a<a.b.b-1;}
function xB(a){if(a.a>=a.b.b){throw new gL();}return a.b.a[++a.a];}
function yB(){return wB(this);}
function zB(){return xB(this);}
function tB(){}
_=tB.prototype=new sF();_.db=yB;_.hb=zB;_.tN=qL+'WidgetCollection$WidgetIterator';_.tI=93;_.a=(-1);function oC(b,a){return gC(new eC(),a,b);}
function fC(a){{iC(a);}}
function gC(a,b,c){a.b=b;fC(a);return a;}
function iC(a){++a.a;while(a.a<a.b.a){if(a.b[a.a]!==null){return;}++a.a;}}
function jC(a){return a.a<a.b.a;}
function kC(a){var b;if(!jC(a)){throw new gL();}b=a.b[a.a];iC(a);return b;}
function lC(){return jC(this);}
function mC(){return kC(this);}
function eC(){}
_=eC.prototype=new sF();_.db=lC;_.hb=mC;_.tN=qL+'WidgetIterators$1';_.tI=94;_.a=(-1);function hD(c,f,b,e,g,a){var d;d=kh();Ci(d,dD(c,f,b,e,g,a));return ki(d);}
function EC(){}
_=EC.prototype=new sF();_.tN=rL+'ClippedImageImpl';_.tI=95;function cD(){cD=kL;fD=bG(u(),'https')?'https://':'http://';}
function aD(a){cD();eD();return a;}
function bD(g,a,i,f,h,j,b){var c,d,e;Fi(a,'width',j+'px');Fi(a,'height',b+'px');c=ki(a);Fi(c,'filter',"progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+i+"',sizingMethod='crop')");Fi(c,'marginLeft',-f+'px');Fi(c,'marginTop',-h+'px');e=f+j;d=h+b;yi(c,'width',e);yi(c,'height',d);}
function dD(f,h,e,g,i,c){var a,b,d;b='overflow: hidden; width: '+i+'px; height: '+c+'px; padding: 0px; zoom: 1';d="filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+h+"',sizingMethod='crop'); margin-left: "+ -e+'px; margin-top: '+ -g+'px; border: none';a='<gwt:clipper style="'+b+'"><img src=\''+fD+"' onerror='if(window.__gwt_transparentImgHandler)window.__gwt_transparentImgHandler(this);else this.src=\""+v()+'clear.cache.gif"\' style="'+d+'" width='+(e+i)+' height='+(g+c)+" border='0'><\/gwt:clipper>";return a;}
function eD(){cD();$wnd.__gwt_transparentImgHandler=function(a){a.onerror=null;Bi(a,v()+'clear.cache.gif');};}
function FC(){}
_=FC.prototype=new EC();_.tN=rL+'ClippedImageImplIE6';_.tI=96;var fD;function lD(){lD=kL;oD=aD(new FC());}
function jD(c,e,b,d,f,a){lD();c.d=e;c.b=b;c.c=d;c.e=f;c.a=a;return c;}
function kD(b,a){av(a,b.d,b.b,b.c,b.e,b.a);}
function mD(a){return Du(new lu(),a.d,a.b,a.c,a.e,a.a);}
function nD(a){return dD(oD,a.d,a.b,a.c,a.e,a.a);}
function iD(){}
_=iD.prototype=new cn();_.tN=rL+'ClippedImagePrototype';_.tI=97;_.a=0;_.b=0;_.c=0;_.d=null;_.e=0;var oD;function wD(){wD=kL;yD=rD(new qD());zD=yD;}
function uD(a){wD();return a;}
function vD(b,a){a.blur();}
function xD(b){var a=$doc.createElement('DIV');a.tabIndex=0;return a;}
function pD(){}
_=pD.prototype=new sF();_.tN=rL+'FocusImpl';_.tI=98;var yD,zD;function sD(){sD=kL;wD();}
function rD(a){sD();uD(a);return a;}
function tD(c,b){try{b.focus();}catch(a){if(!b|| !b.focus){throw a;}}}
function qD(){}
_=qD.prototype=new pD();_.tN=rL+'FocusImplIE6';_.tI=99;function bE(a){return fh();}
function AD(){}
_=AD.prototype=new sF();_.tN=rL+'PopupImpl';_.tI=100;function DD(c,b){var a=b.__frame;a.parentElement.removeChild(a);b.__frame=null;a.__popup=null;}
function ED(d,b){var a=$doc.createElement('iframe');a.src="javascript:''";a.scrolling='no';a.frameBorder=0;b.__frame=a;a.__popup=b;var c=a.style;c.position='absolute';c.filter='alpha(opacity=0)';c.visibility=b.style.visibility;c.left=b.offsetLeft;c.top=b.offsetTop;c.width=b.offsetWidth;c.height=b.offsetHeight;c.setExpression('left','this.__popup.offsetLeft');c.setExpression('top','this.__popup.offsetTop');c.setExpression('width','this.__popup.offsetWidth');c.setExpression('height','this.__popup.offsetHeight');b.parentElement.insertBefore(a,b);}
function FD(b,a,c){if(a.__frame){a.__frame.style.visibility=c?'visible':'hidden';}}
function BD(){}
_=BD.prototype=new AD();_.tN=rL+'PopupImplIE6';_.tI=101;function dE(){}
_=dE.prototype=new wF();_.tN=sL+'ArrayStoreException';_.tI=102;function iE(a,b){if(b<2||b>36){return (-1);}if(a>=48&&a<48+cF(b,10)){return a-48;}if(a>=97&&a<b+97-10){return a-97+10;}if(a>=65&&a<b+65-10){return a-65+10;}return (-1);}
function jE(){}
_=jE.prototype=new wF();_.tN=sL+'ClassCastException';_.tI=103;function rE(b,a){xF(b,a);return b;}
function qE(){}
_=qE.prototype=new wF();_.tN=sL+'IllegalArgumentException';_.tI=104;function uE(b,a){xF(b,a);return b;}
function tE(){}
_=tE.prototype=new wF();_.tN=sL+'IllegalStateException';_.tI=105;function xE(b,a){xF(b,a);return b;}
function wE(){}
_=wE.prototype=new wF();_.tN=sL+'IndexOutOfBoundsException';_.tI=106;function mF(){mF=kL;{rF();}}
function nF(a){mF();return isNaN(a);}
function oF(e,d,c,h){mF();var a,b,f,g;if(e===null){throw kF(new jF(),'Unable to parse null');}b=aG(e);f=b>0&&AF(e,0)==45?1:0;for(a=f;a<b;a++){if(iE(AF(e,a),d)==(-1)){throw kF(new jF(),'Could not parse '+e+' in radix '+d);}}g=pF(e,d);if(nF(g)){throw kF(new jF(),'Unable to parse '+e);}else if(g<c||g>h){throw kF(new jF(),'The string '+e+' exceeds the range for the requested data type');}return g;}
function pF(b,a){mF();return parseInt(b,a);}
function rF(){mF();qF=/^[+-]?\d*\.?\d*(e[+-]?\d+)?$/i;}
var qF=null;function AE(){AE=kL;mF();}
function DE(a){AE();return EE(a,10);}
function EE(b,a){AE();return Cb(oF(b,a,(-2147483648),2147483647));}
var BE=2147483647,CE=(-2147483648);function bF(a){return a<0?-a:a;}
function cF(a,b){return a<b?a:b;}
function dF(){}
_=dF.prototype=new wF();_.tN=sL+'NegativeArraySizeException';_.tI=107;function gF(b,a){xF(b,a);return b;}
function fF(){}
_=fF.prototype=new wF();_.tN=sL+'NullPointerException';_.tI=108;function kF(b,a){rE(b,a);return b;}
function jF(){}
_=jF.prototype=new qE();_.tN=sL+'NumberFormatException';_.tI=109;function AF(b,a){return b.charCodeAt(a);}
function DF(b,a){if(!Ab(a,1))return false;return fG(b,a);}
function CF(b,a){if(a==null)return false;return b==a||b.toLowerCase()==a.toLowerCase();}
function EF(b,a){return b.indexOf(a);}
function FF(c,b,a){return c.indexOf(b,a);}
function aG(a){return a.length;}
function bG(b,a){return EF(b,a)==0;}
function cG(b,a){return b.substr(a,b.length-a);}
function dG(c,a,b){return c.substr(a,b-a);}
function eG(c){var a=c.replace(/^(\s*)/,'');var b=a.replace(/\s*$/,'');return b;}
function fG(a,b){return String(a)==b;}
function gG(a){return DF(this,a);}
function iG(){var a=hG;if(!a){a=hG={};}var e=':'+this;var b=a[e];if(b==null){b=0;var f=this.length;var d=f<64?1:f/32|0;for(var c=0;c<f;c+=d){b<<=1;b+=this.charCodeAt(c);}b|=0;a[e]=b;}return b;}
_=String.prototype;_.eQ=gG;_.hC=iG;_.tN=sL+'String';_.tI=2;var hG=null;function lG(){return new Date().getTime();}
function mG(a){return B(a);}
function rG(b,a){xF(b,a);return b;}
function qG(){}
_=qG.prototype=new wF();_.tN=sL+'UnsupportedOperationException';_.tI=111;function BG(b,a){b.c=a;return b;}
function DG(a){return a.a<a.c.fc();}
function EG(a){if(!DG(a)){throw new gL();}return a.c.bb(a.b=a.a++);}
function FG(a){if(a.b<0){throw new tE();}a.c.Ab(a.b);a.a=a.b;a.b=(-1);}
function aH(){return DG(this);}
function bH(){return EG(this);}
function AG(){}
_=AG.prototype=new sF();_.db=aH;_.hb=bH;_.tN=tL+'AbstractList$IteratorImpl';_.tI=112;_.a=0;_.b=(-1);function jI(f,d,e){var a,b,c;for(b=gK(f.z());FJ(b);){a=aK(b);c=a.E();if(d===null?c===null:d.eQ(c)){if(e){bK(b);}return a;}}return null;}
function kI(b){var a;a=b.z();return nH(new mH(),b,a);}
function lI(b){var a;a=qK(b);return BH(new AH(),b,a);}
function mI(a){return jI(this,a,false)!==null;}
function nI(d){var a,b,c,e,f,g,h;if(d===this){return true;}if(!Ab(d,26)){return false;}f=zb(d,26);c=kI(this);e=f.gb();if(!tI(c,e)){return false;}for(a=pH(c);wH(a);){b=xH(a);h=this.cb(b);g=f.cb(b);if(h===null?g!==null:!h.eQ(g)){return false;}}return true;}
function oI(b){var a;a=jI(this,b,false);return a===null?null:a.ab();}
function pI(){var a,b,c;b=0;for(c=gK(this.z());FJ(c);){a=aK(c);b+=a.hC();}return b;}
function qI(){return kI(this);}
function lH(){}
_=lH.prototype=new sF();_.v=mI;_.eQ=nI;_.cb=oI;_.hC=pI;_.gb=qI;_.tN=tL+'AbstractMap';_.tI=113;function tI(e,b){var a,c,d;if(b===e){return true;}if(!Ab(b,27)){return false;}c=zb(b,27);if(c.fc()!=e.fc()){return false;}for(a=c.fb();a.db();){d=a.hb();if(!e.w(d)){return false;}}return true;}
function uI(a){return tI(this,a);}
function vI(){var a,b,c;a=0;for(b=this.fb();b.db();){c=b.hb();if(c!==null){a+=c.hC();}}return a;}
function rI(){}
_=rI.prototype=new tG();_.eQ=uI;_.hC=vI;_.tN=tL+'AbstractSet';_.tI=114;function nH(b,a,c){b.a=a;b.b=c;return b;}
function pH(b){var a;a=gK(b.b);return uH(new tH(),b,a);}
function qH(a){return this.a.v(a);}
function rH(){return pH(this);}
function sH(){return this.b.a.c;}
function mH(){}
_=mH.prototype=new rI();_.w=qH;_.fb=rH;_.fc=sH;_.tN=tL+'AbstractMap$1';_.tI=115;function uH(b,a,c){b.a=c;return b;}
function wH(a){return a.a.db();}
function xH(b){var a;a=b.a.hb();return a.E();}
function yH(){return wH(this);}
function zH(){return xH(this);}
function tH(){}
_=tH.prototype=new sF();_.db=yH;_.hb=zH;_.tN=tL+'AbstractMap$2';_.tI=116;function BH(b,a,c){b.a=a;b.b=c;return b;}
function DH(b){var a;a=gK(b.b);return cI(new bI(),b,a);}
function EH(a){return pK(this.a,a);}
function FH(){return DH(this);}
function aI(){return this.b.a.c;}
function AH(){}
_=AH.prototype=new tG();_.w=EH;_.fb=FH;_.fc=aI;_.tN=tL+'AbstractMap$3';_.tI=117;function cI(b,a,c){b.a=c;return b;}
function eI(a){return a.a.db();}
function fI(a){var b;b=a.a.hb().ab();return b;}
function gI(){return eI(this);}
function hI(){return fI(this);}
function bI(){}
_=bI.prototype=new sF();_.db=gI;_.hb=hI;_.tN=tL+'AbstractMap$4';_.tI=118;function nK(){nK=kL;uK=AK();}
function kK(a){{mK(a);}}
function lK(a){nK();kK(a);return a;}
function mK(a){a.a=eb();a.d=fb();a.b=ac(uK,ab);a.c=0;}
function oK(b,a){if(Ab(a,1)){return EK(b.d,zb(a,1))!==uK;}else if(a===null){return b.b!==uK;}else{return DK(b.a,a,a.hC())!==uK;}}
function pK(a,b){if(a.b!==uK&&CK(a.b,b)){return true;}else if(zK(a.d,b)){return true;}else if(xK(a.a,b)){return true;}return false;}
function qK(a){return eK(new BJ(),a);}
function rK(c,a){var b;if(Ab(a,1)){b=EK(c.d,zb(a,1));}else if(a===null){b=c.b;}else{b=DK(c.a,a,a.hC());}return b===uK?null:b;}
function sK(c,a,d){var b;{b=c.b;c.b=d;}if(b===uK){++c.c;return null;}else{return b;}}
function tK(c,a){var b;if(Ab(a,1)){b=bL(c.d,zb(a,1));}else if(a===null){b=c.b;c.b=ac(uK,ab);}else{b=aL(c.a,a,a.hC());}if(b===uK){return null;}else{--c.c;return b;}}
function vK(e,c){nK();for(var d in e){if(d==parseInt(d)){var a=e[d];for(var f=0,b=a.length;f<b;++f){c.u(a[f]);}}}}
function wK(d,a){nK();for(var c in d){if(c.charCodeAt(0)==58){var e=d[c];var b=wJ(c.substring(1),e);a.u(b);}}}
function xK(f,h){nK();for(var e in f){if(e==parseInt(e)){var a=f[e];for(var g=0,b=a.length;g<b;++g){var c=a[g];var d=c.ab();if(CK(h,d)){return true;}}}}return false;}
function yK(a){return oK(this,a);}
function zK(c,d){nK();for(var b in c){if(b.charCodeAt(0)==58){var a=c[b];if(CK(d,a)){return true;}}}return false;}
function AK(){nK();}
function BK(){return qK(this);}
function CK(a,b){nK();if(a===b){return true;}else if(a===null){return false;}else{return a.eQ(b);}}
function FK(a){return rK(this,a);}
function DK(f,h,e){nK();var a=f[e];if(a){for(var g=0,b=a.length;g<b;++g){var c=a[g];var d=c.E();if(CK(h,d)){return c.ab();}}}}
function EK(b,a){nK();return b[':'+a];}
function aL(f,h,e){nK();var a=f[e];if(a){for(var g=0,b=a.length;g<b;++g){var c=a[g];var d=c.E();if(CK(h,d)){if(a.length==1){delete f[e];}else{a.splice(g,1);}return c.ab();}}}}
function bL(c,a){nK();a=':'+a;var b=c[a];delete c[a];return b;}
function sJ(){}
_=sJ.prototype=new lH();_.v=yK;_.z=BK;_.cb=FK;_.tN=tL+'HashMap';_.tI=119;_.a=null;_.b=null;_.c=0;_.d=null;var uK;function uJ(b,a,c){b.a=a;b.b=c;return b;}
function wJ(a,b){return uJ(new tJ(),a,b);}
function xJ(b){var a;if(Ab(b,28)){a=zb(b,28);if(CK(this.a,a.E())&&CK(this.b,a.ab())){return true;}}return false;}
function yJ(){return this.a;}
function zJ(){return this.b;}
function AJ(){var a,b;a=0;b=0;if(this.a!==null){a=this.a.hC();}if(this.b!==null){b=this.b.hC();}return a^b;}
function tJ(){}
_=tJ.prototype=new sF();_.eQ=xJ;_.E=yJ;_.ab=zJ;_.hC=AJ;_.tN=tL+'HashMap$EntryImpl';_.tI=120;_.a=null;_.b=null;function eK(b,a){b.a=a;return b;}
function gK(a){return DJ(new CJ(),a.a);}
function hK(c){var a,b,d;if(Ab(c,28)){a=zb(c,28);b=a.E();if(oK(this.a,b)){d=rK(this.a,b);return CK(a.ab(),d);}}return false;}
function iK(){return gK(this);}
function jK(){return this.a.c;}
function BJ(){}
_=BJ.prototype=new rI();_.w=hK;_.fb=iK;_.fc=jK;_.tN=tL+'HashMap$EntrySet';_.tI=121;function DJ(c,b){var a;c.c=b;a=yI(new wI());if(c.c.b!==(nK(),uK)){zI(a,uJ(new tJ(),null,c.c.b));}wK(c.c.d,a);vK(c.c.a,a);c.a=eH(a);return c;}
function FJ(a){return DG(a.a);}
function aK(a){return a.b=zb(EG(a.a),28);}
function bK(a){if(a.b===null){throw uE(new tE(),'Must call next() before remove().');}else{FG(a.a);tK(a.c,a.b.E());a.b=null;}}
function cK(){return FJ(this);}
function dK(){return aK(this);}
function CJ(){}
_=CJ.prototype=new sF();_.db=cK;_.hb=dK;_.tN=tL+'HashMap$EntrySetIterator';_.tI=122;_.a=null;_.b=null;function gL(){}
_=gL.prototype=new wF();_.tN=tL+'NoSuchElementException';_.tI=123;function cE(){ge(de(new Bc()));}
function gwtOnLoad(b,d,c){$moduleName=d;$moduleBase=c;if(b)try{cE();}catch(a){b(d);}else{cE();}}
var Fb=[{},{18:1},{1:1,18:1,23:1,24:1},{3:1,18:1},{3:1,18:1},{3:1,18:1},{3:1,18:1},{2:1,18:1},{18:1},{18:1},{18:1},{18:1,19:1},{12:1,18:1,19:1,20:1},{12:1,17:1,18:1,19:1,20:1},{12:1,17:1,18:1,19:1,20:1},{6:1,12:1,17:1,18:1,19:1,20:1},{6:1,12:1,13:1,17:1,18:1,19:1,20:1},{6:1,12:1,13:1,17:1,18:1,19:1,20:1},{11:1,18:1},{12:1,18:1,19:1,20:1},{12:1,18:1,19:1,20:1},{11:1,18:1},{18:1,22:1},{6:1,12:1,17:1,18:1,19:1,20:1},{10:1,18:1},{5:1,18:1},{12:1,18:1,19:1,20:1},{4:1,18:1},{11:1,12:1,15:1,18:1,19:1,20:1},{18:1},{12:1,18:1,19:1,20:1},{12:1,18:1,19:1,20:1},{12:1,17:1,18:1,19:1,20:1},{12:1,17:1,18:1,19:1,20:1},{12:1,17:1,18:1,19:1,20:1},{12:1,18:1,19:1,20:1},{11:1,12:1,18:1,19:1,20:1},{3:1,18:1},{18:1},{8:1,18:1},{8:1,18:1},{8:1,18:1},{18:1},{2:1,7:1,18:1},{2:1,18:1},{9:1,18:1},{18:1},{18:1},{12:1,17:1,18:1,19:1,20:1},{18:1},{12:1,18:1,19:1,20:1},{12:1,18:1,19:1,20:1},{12:1,18:1,19:1,20:1},{12:1,17:1,18:1,19:1,20:1},{12:1,18:1,19:1,20:1},{18:1},{18:1,25:1},{18:1,25:1},{18:1,25:1},{12:1,17:1,18:1,19:1,20:1},{18:1},{18:1},{18:1,21:1},{12:1,17:1,18:1,19:1,20:1},{12:1,17:1,18:1,19:1,20:1},{18:1},{18:1},{12:1,18:1,19:1,20:1},{12:1,18:1,19:1,20:1},{18:1},{18:1},{18:1},{18:1},{18:1},{18:1},{18:1},{12:1,17:1,18:1,19:1,20:1},{12:1,18:1,19:1,20:1},{5:1,18:1},{18:1},{18:1},{18:1},{18:1,25:1},{12:1,14:1,17:1,18:1,19:1,20:1},{9:1,18:1},{12:1,17:1,18:1,19:1,20:1},{18:1},{18:1,25:1},{12:1,17:1,18:1,19:1,20:1},{16:1,18:1,19:1},{16:1,18:1,19:1},{12:1,17:1,18:1,19:1,20:1},{18:1},{18:1},{18:1},{18:1},{18:1},{18:1},{18:1},{18:1},{18:1},{18:1},{3:1,18:1},{3:1,18:1},{3:1,18:1},{3:1,18:1},{3:1,18:1},{3:1,18:1},{3:1,18:1},{3:1,18:1},{18:1,24:1},{3:1,18:1},{18:1},{18:1,26:1},{18:1,27:1},{18:1,27:1},{18:1},{18:1},{18:1},{18:1,26:1},{18:1,28:1},{18:1,27:1},{18:1},{3:1,18:1},{18:1},{18:1},{18:1},{18:1},{18:1},{18:1},{18:1},{18:1},{18:1}];if (com_google_gwt_sample_mail_Mail) {  var __gwt_initHandlers = com_google_gwt_sample_mail_Mail.__gwt_initHandlers;  com_google_gwt_sample_mail_Mail.onScriptLoad(gwtOnLoad);}})();
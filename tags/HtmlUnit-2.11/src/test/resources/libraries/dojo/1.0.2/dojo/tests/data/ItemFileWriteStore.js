if(!dojo._hasResource["tests.data.ItemFileWriteStore"]){ //_hasResource checks added by build. Do not use _hasResource directly in your code.
dojo._hasResource["tests.data.ItemFileWriteStore"] = true;
dojo.provide("tests.data.ItemFileWriteStore");
dojo.require("tests.data.readOnlyItemFileTestTemplates");

dojo.require("dojo.data.ItemFileWriteStore");
dojo.require("dojo.data.api.Read");
dojo.require("dojo.data.api.Identity");
dojo.require("dojo.data.api.Write");
dojo.require("dojo.data.api.Notification");


// First, make sure ItemFileWriteStore can still pass all the same unit tests 
// that we use for its superclass, ItemFileReadStore:
tests.data.readOnlyItemFileTestTemplates.registerTestsForDatastore("dojo.data.ItemFileWriteStore");


// Now run some tests that are specific to the write-access features:
doh.register("tests.data.ItemFileWriteStore", 
	[
		function test_getFeatures(){
			//	summary: 
			//		Simple test of the getFeatures function of the store
			//	description:
			//		Simple test of the getFeatures function of the store
			var store = new dojo.data.ItemFileWriteStore(tests.data.readOnlyItemFileTestTemplates.getTestData("countries"));

			var features = store.getFeatures(); 

			// make sure we have the expected features:
			doh.assertTrue(features["dojo.data.api.Read"] != null);
			doh.assertTrue(features["dojo.data.api.Identity"] != null);
			doh.assertTrue(features["dojo.data.api.Write"] != null);
			doh.assertTrue(features["dojo.data.api.Notification"] != null);
			doh.assertFalse(features["iggy"]);
			
			// and only the expected features:
			var count = 0;
			for(var i in features){
				doh.assertTrue((i === "dojo.data.api.Read" || 
					i === "dojo.data.api.Identity" || 
					i === "dojo.data.api.Write" || 
					i === "dojo.data.api.Notification"));
				count++;
			}
			doh.assertEqual(count, 4);
		},
		function testWriteAPI_setValue(){
			//	summary: 
			//		Simple test of the setValue API
			//	description:
			//		Simple test of the setValue API
			var store = new dojo.data.ItemFileWriteStore(tests.data.readOnlyItemFileTestTemplates.getTestData("countries"));

			var deferred = new doh.Deferred();
			function onComplete(items, request){
				doh.assertEqual(1, items.length);
				var item = items[0];
				doh.assertTrue(store.containsValue(item, "capital", "Cairo"));
				
				// FIXME:  
				//    Okay, so this seems very odd.  Maybe I'm just being dense.
				//    These tests works:
				doh.assertEqual(store.isDirty(item), false);
				doh.assertTrue(store.isDirty(item) == false);
				//    But these seemingly equivalent tests will not work:
				// doh.assertFalse(store.isDirty(item));
				// doh.assertTrue(!(store.isDirty(item)));
				//   
				//    All of which seems especially weird, given that this *does* work:
				doh.assertFalse(store.isDirty());
				
				
				doh.assertTrue(store.isDirty(item) == false);
				doh.assertTrue(!store.isDirty());
				store.setValue(item, "capital", "New Cairo");
				doh.assertTrue(store.isDirty(item));
				doh.assertTrue(store.isDirty());
				doh.assertEqual(store.getValue(item, "capital").toString(), "New Cairo");
				deferred.callback(true);
			}
			function onError(error, request){
				deferred.errback(error);
			}
			store.fetch({query:{name:"Egypt"}, onComplete: onComplete, onError: onError});
			return deferred; //Object
		},
		function testWriteAPI_setValues(){
			//	summary: 
			//		Simple test of the setValues API
			//	description:
			//		Simple test of the setValues API
			var store = new dojo.data.ItemFileWriteStore(tests.data.readOnlyItemFileTestTemplates.getTestData("countries"));

			var deferred = new doh.Deferred();
			function onComplete(items, request){
				doh.assertEqual(1, items.length);
				var item = items[0];
				doh.assertTrue(store.containsValue(item, "name", "Egypt"));
				doh.assertTrue(store.isDirty(item) == false);
				doh.assertTrue(!store.isDirty());
				store.setValues(item, "name", ["Egypt 1", "Egypt 2"]);
				doh.assertTrue(store.isDirty(item));
				doh.assertTrue(store.isDirty());
				var values = store.getValues(item, "name");
				doh.assertTrue(values[0] == "Egypt 1");
				doh.assertTrue(values[1] == "Egypt 2");
				deferred.callback(true);
			}
			function onError(error, request){
				deferred.errback(error);
			}
			store.fetch({query:{name:"Egypt"}, onComplete: onComplete, onError: onError});
			return deferred; //Object
		},
		function testWriteAPI_unsetAttribute(){
			//	summary: 
			//		Simple test of the unsetAttribute API
			//	description:
			//		Simple test of the unsetAttribute API
			var store = new dojo.data.ItemFileWriteStore(tests.data.readOnlyItemFileTestTemplates.getTestData("countries"));

			var deferred = new doh.Deferred();
			function onComplete(items, request) {
				doh.assertEqual(1, items.length);
				var item = items[0];
				doh.assertTrue(store.containsValue(item, "name", "Egypt"));
				doh.assertTrue(store.isDirty(item) == false);
				doh.assertTrue(!store.isDirty());
				store.unsetAttribute(item, "name");
				doh.assertTrue(store.isDirty(item));
				doh.assertTrue(store.isDirty());
				doh.assertTrue(!store.hasAttribute(item, "name"));
				deferred.callback(true);
			}
			function onError(error, request) {
				deferred.errback(error);
			}
			store.fetch({query:{name:"Egypt"}, onComplete: onComplete, onError: onError});
			return deferred; //Object
		},
		function testWriteAPI_newItem(){
			//	summary: 
			//		Simple test of the newItem API
			//	description:
			//		Simple test of the newItem API
			var store = new dojo.data.ItemFileWriteStore(tests.data.readOnlyItemFileTestTemplates.getTestData("countries"));

			var deferred = new doh.Deferred();
			doh.assertTrue(!store.isDirty());

			var onNewInvoked = false;
			store.onNew = function(newItem, parentInfo){

				doh.assertTrue(newItem !== null);
				doh.assertTrue(parentInfo === null);
				doh.assertTrue(store.isItem(newItem));
				onNewInvoked = true;
			};
			var canada = store.newItem({name: "Canada", abbr:"ca", capital:"Ottawa"});
			doh.assertTrue(onNewInvoked);
			
			doh.assertTrue(store.isDirty(canada));
			doh.assertTrue(store.isDirty());
			doh.assertTrue(store.getValues(canada, "name") == "Canada");
			function onComplete(items, request){
				doh.assertEqual(1, items.length);
				var item = items[0];
				doh.assertTrue(store.containsValue(item, "name", "Canada"));
				deferred.callback(true);
			}
			function onError(error, request){
				deferred.errback(error);
			}
			store.fetch({query:{name:"Canada"}, onComplete: onComplete, onError: onError});
			return deferred; //Object
		},
		function testWriteAPI_newItem_withParent(){
			//	summary: 
			//		Simple test of the newItem API with a parent assignment
			//	description:
			//		Simple test of the newItem API with a parent assignment
			var store = new dojo.data.ItemFileWriteStore(tests.data.readOnlyItemFileTestTemplates.getTestData("countries"));

			var deferred = new doh.Deferred();
			doh.assertTrue(!store.isDirty());
			function onComplete(items, request){
				doh.assertEqual(1, items.length);
				var item = items[0];
				doh.assertTrue(store.containsValue(item, "name", "Egypt"));

				//Attach an onNew to validate we get expected values.
				var onNewInvoked = false;
				store.onNew = function(newItem, parentInfo){
					doh.assertEqual(item, parentInfo.item);
					doh.assertEqual("cities", parentInfo.attribute);
					doh.assertTrue(parentInfo.oldValue === undefined);
					doh.assertTrue(parentInfo.newValue === newItem);
					onNewInvoked = true;
				};
                
				//Attach an onSet and verify onSet is NOT called in this case.
				store.onSet = function(item, attribute, oldValue, newValue){
					doh.assertTrue(false);
				};

				//See if we can add in a new item representing the city of Cairo.
				//This should also call the onNew set above....
				var newItem = store.newItem({name: "Cairo", abbr: "Cairo"}, {parent: item, attribute: "cities"});
				doh.assertTrue(onNewInvoked);

				function onCompleteNewItemShallow(items, request){
					doh.assertEqual(0, items.length);
					function onCompleteNewItemDeep(items, request){
						doh.assertEqual(1, items.length);
						var item = items[0];
						doh.assertEqual("Cairo", store.getValue(item, "name"));
						deferred.callback(true);
					}
					//Do a deep search now, should find the new item of the city with name attribute Cairo.
					store.fetch({query:{name:"Cairo"}, onComplete: onCompleteNewItemDeep, onError: onError, queryOptions: {deep:true}});
				}
				//Do a shallow search first, should find nothing.
				store.fetch({query:{name:"Cairo"}, onComplete: onCompleteNewItemShallow, onError: onError});
			}
			function onError(error, request){
				deferred.errback(error);
			}
			store.fetch({query:{name:"Egypt"}, onComplete: onComplete, onError: onError});
			return deferred; //Object
		},
		
		function testWriteAPI_newItem_multiple_withParent(){
			//	summary: 
			//		Simple test of the newItem API with a parent assignment multiple times.
			//	description:
			//		Simple test of the newItem API with a parent assignment multiple times.
			var store = new dojo.data.ItemFileWriteStore(tests.data.readOnlyItemFileTestTemplates.getTestData("countries"));

			var deferred = new doh.Deferred();
			
			doh.assertTrue(!store.isDirty());
			
			function onComplete(items, request){
				doh.assertEqual(1, items.length);
				var item = items[0];
				doh.assertTrue(store.containsValue(item, "name", "Egypt"));

				//Attach an onNew to validate we get expected values.
				store.onNew = function(newItem, parentInfo){
					doh.assertEqual(item, parentInfo.item);
					doh.assertEqual("cities", parentInfo.attribute);
					
					doh.assertTrue(parentInfo.oldValue === undefined);
					
					doh.assertTrue(parentInfo.newValue === newItem);
				};

				//See if we can add in a new item representing the city of Cairo.
				//This should also call the onNew set above....
				var newItem1 = store.newItem({name: "Cairo", abbr: "Cairo"}, {parent: item, attribute: "cities"});
				
				//Attach a new onNew to validate we get expected values.
				store.onNew = function(newItem, parentInfo){
					doh.assertEqual(item, parentInfo.item);
					doh.assertEqual("cities", parentInfo.attribute);
					
					console.log(parentInfo.oldValue);
					doh.assertTrue(parentInfo.oldValue == newItem1);
					
					doh.assertTrue(parentInfo.newValue[0] == newItem1);
					doh.assertTrue(parentInfo.newValue[1] == newItem);
				};
				var newItem2 = store.newItem({name: "Banha", abbr: "Banha"}, {parent: item, attribute: "cities"});
				
				//Attach a new onNew to validate we get expected values.
				store.onNew = function(newItem, parentInfo){
					doh.assertEqual(item, parentInfo.item);
					doh.assertEqual("cities", parentInfo.attribute);
					
					doh.assertTrue(parentInfo.oldValue[0] == newItem1);
					doh.assertTrue(parentInfo.oldValue[1] == newItem2);
					
					doh.assertTrue(parentInfo.newValue[0] == newItem1);
					doh.assertTrue(parentInfo.newValue[1] == newItem2);
					doh.assertTrue(parentInfo.newValue[2] == newItem);
				};
				var newItem3 = store.newItem({name: "Damanhur", abbr: "Damanhur"}, {parent: item, attribute: "cities"});
				deferred.callback(true);
			}
			function onError(error, request){
				deferred.errback(error);
			}
			store.fetch({query:{name:"Egypt"}, onComplete: onComplete, onError: onError});
			return deferred; //Object
		},

		function testWriteAPI_deleteItem(){
			//	summary: 
			//		Simple test of the deleteItem API
			//	description:
			//		Simple test of the deleteItem API
			var store = new dojo.data.ItemFileWriteStore(tests.data.readOnlyItemFileTestTemplates.getTestData("countries"));

			var deferred = new doh.Deferred();
			function onComplete(items, request){
				doh.assertEqual(1, items.length);
				var item = items[0];
				doh.assertTrue(store.containsValue(item, "name", "Egypt"));
				doh.assertTrue(store.isDirty(item) == false);
				doh.assertTrue(!store.isDirty());
				store.deleteItem(item);
				doh.assertTrue(store.isDirty(item));
				doh.assertTrue(store.isDirty());
				function onCompleteToo(itemsToo, requestToo) {
					doh.assertEqual(0, itemsToo.length);
					deferred.callback(true);
				}
				store.fetch({query:{name:"Egypt"}, onComplete: onCompleteToo, onError: onError});
			}
			function onError(error, request){
				deferred.errback(error);
			}
			store.fetch({query:{name:"Egypt"}, onComplete: onComplete, onError: onError});
			return deferred; //Object
		},
		function testWriteAPI_isDirty(){
			//	summary: 
			//		Simple test of the isDirty API
			//	description:
			//		Simple test of the isDirty API
			var store = new dojo.data.ItemFileWriteStore(tests.data.readOnlyItemFileTestTemplates.getTestData("countries"));

			var deferred = new doh.Deferred();
			function onComplete(items, request) {
				doh.assertEqual(1, items.length);
				var item = items[0];
				doh.assertTrue(store.containsValue(item, "name", "Egypt"));
				store.setValue(item, "name", "Egypt 2");
				doh.assertTrue(store.getValue(item, "name") == "Egypt 2");
				doh.assertTrue(store.isDirty(item));
				deferred.callback(true);
			}
			function onError(error, request) {
				deferred.errback(error);
			}
			store.fetch({query:{name:"Egypt"}, onComplete: onComplete, onError: onError});
			return deferred; //Object
		},
		function testWriteAPI_revert(){
			//	summary: 
			//		Simple test of the revert API
			//	description:
			//		Simple test of the revert API
			var store = new dojo.data.ItemFileWriteStore(tests.data.readOnlyItemFileTestTemplates.getTestData("countries"));

			var deferred = new doh.Deferred();
			function onComplete(items, request) {
				doh.assertEqual(1, items.length);
				var item = items[0];
				doh.assertTrue(store.containsValue(item, "name", "Egypt"));
				doh.assertTrue(store.isDirty(item) == false);
				doh.assertTrue(!store.isDirty());
				store.setValue(item, "name", "Egypt 2");
				doh.assertTrue(store.getValue(item, "name") == "Egypt 2");
				doh.assertTrue(store.isDirty(item));
				doh.assertTrue(store.isDirty());
				store.revert();
				
				//Fetch again to see if it reset the state.
				function onCompleteToo(itemsToo, requestToo){
					doh.assertEqual(1, itemsToo.length);
					var itemToo = itemsToo[0];
					doh.assertTrue(store.containsValue(itemToo, "name", "Egypt"));
					deferred.callback(true);
				}
				store.fetch({query:{name:"Egypt"}, onComplete: onCompleteToo, onError: onError});
			}
			function onError(error, request){
				deferred.errback(error);
			}
			store.fetch({query:{name:"Egypt"}, onComplete: onComplete, onError: onError});
			return deferred; //Object
		},
		function testWriteAPI_save(){
			//	summary: 
			//		Simple test of the save API
			//	description:
			//		Simple test of the save API
			var store = new dojo.data.ItemFileWriteStore(tests.data.readOnlyItemFileTestTemplates.getTestData("countries"));

			var deferred = new doh.Deferred();
			function onError(error){
				deferred.errback(error);
			}
			function onItem(item){
				store.setValue(item, "capital", "New Cairo");
				function onComplete() {
					deferred.callback(true);
				}
				store.save({onComplete:onComplete, onError:onError});
			}
			store.fetchItemByIdentity({identity:"eg", onItem:onItem, onError:onError});
			return deferred; //Object
		},
		function testWriteAPI_saveVerifyState(){
			//	summary: 
			//		Simple test of the save API
			//	description:
			//		Simple test of the save API
			var store = new dojo.data.ItemFileWriteStore(tests.data.readOnlyItemFileTestTemplates.getTestData("countries"));

			var deferred = new doh.Deferred();
			function onError(error){
				deferred.errback(error);
			}
			function onItem(item){
				store.setValue(item, "capital", "New Cairo");
				function onComplete() {
					//Check internal state.  Note:  Users should NOT do this, this is a UT verification
					//of internals in this case.  Ref tracker: #4394
					doh.assertTrue(!store._saveInProgress);
					deferred.callback(true);
				}
				store.save({onComplete:onComplete, onError:onError});
			}
			store.fetchItemByIdentity({identity:"eg", onItem:onItem, onError:onError});
			return deferred; //Object
		},
		function testWriteAPI_saveEverything(){
			//	summary: 
			//		Simple test of the save API
			//	description:
			//		Simple test of the save API
			var store = new dojo.data.ItemFileWriteStore(tests.data.readOnlyItemFileTestTemplates.getTestData("countries"));
			var egypt;
			store._saveEverything = function(saveCompleteCallback, saveFailedCallback, newFileContentString){
				var struct = dojo.fromJson(newFileContentString);
				doh.assertEqual(struct.identifier, store.getIdentityAttributes(egypt)[0]);
				doh.assertEqual(struct.label, store.getLabelAttributes(egypt)[0]);
				doh.assertEqual(struct.items.length, 7);
				
				var cloneStore = new dojo.data.ItemFileWriteStore({data:struct});
				function onItemClone(itemClone){
					var egyptClone = itemClone;
					doh.assertEqual(store.getIdentityAttributes(egypt)[0], cloneStore.getIdentityAttributes(egyptClone)[0]);
					doh.assertEqual(store.getLabelAttributes(egypt)[0], cloneStore.getLabelAttributes(egyptClone)[0]);
					doh.assertEqual(store.getValue(egypt, "name"), cloneStore.getValue(egyptClone, "name"));
				}
				cloneStore.fetchItemByIdentity({identity:"eg", onItem:onItemClone, onError:onError});
				
				saveCompleteCallback();
			};

			var deferred = new doh.Deferred();
			function onError(error){
				deferred.errback(error);
			}
			function onItem(item){
				egypt = item;
				function onComplete() {
					deferred.callback(true);
				}
				store.setValue(egypt, "capital", "New Cairo");
				store.save({onComplete:onComplete, onError:onError});
			}
			store.fetchItemByIdentity({identity:"eg", onItem:onItem, onError:onError});
			return deferred; //Object
		},
		function testWriteAPI_saveEverything_withDateType(){
			//	summary: 
			//		Simple test of the save API	with a non-atomic type (Date) that has a type mapping.
			//	description:
			//		Simple test of the save API	with a non-atomic type (Date) that has a type mapping.
			var store = new dojo.data.ItemFileWriteStore(tests.data.readOnlyItemFileTestTemplates.getTestData("countries"));
			store._saveEverything = function(saveCompleteCallback, saveFailedCallback, newFileContentString){

				//Now load the new data into a datastore and validate that it stored the date right.
				var dataset = dojo.fromJson(newFileContentString);
				var newStore = new dojo.data.ItemFileWriteStore({data: dataset});

				function gotItem(item){
					var independenceDate = newStore.getValue(item,"independence"); 
					doh.assertTrue(independenceDate instanceof Date);
					doh.assertTrue(dojo.date.compare(new Date(1993,04,24), independenceDate, "date") === 0);
					saveCompleteCallback();
				}
				function failed(error, request){
					deferred.errback(error);
					saveFailedCallback();
				}
				newStore.fetchItemByIdentity({identity:"eg", onItem:gotItem, onError:failed});
			};

			var deferred = new doh.Deferred();
			function onError(error){
				deferred.errback(error);
			}
			function onItem(item){
				function onComplete() {
					deferred.callback(true);
				}
				store.setValue(item, "independence", new Date(1993,04,24));
				store.save({onComplete:onComplete, onError:onError});
			}
			store.fetchItemByIdentity({identity:"eg", onItem:onItem, onError:onError});
			return deferred; //Object
		},
		function testWriteAPI_saveEverything_withCustomColorTypeSimple(){
			//	summary: 
			//		Simple test of the save API	with a non-atomic type (dojo.Color) that has a type mapping.
			//	description:
			//		Simple test of the save API	with a non-atomic type (dojo.Color) that has a type mapping.

			//Set up the store basics:  What data it has, and what to do when save is called for saveEverything
			//And how to map the 'Color' type in and out of the format.
			//(Test of saving all to a some location...)
			var dataset = {
				identifier:'name',
				items: [
					{ name:'Kermit', species:'frog', color:{_type:'Color', _value:'green'} },
					{ name:'Beaker', hairColor:{_type:'Color', _value:'red'} }
				]
			};

			var customTypeMap = {'Color': dojo.Color };

			var store = new dojo.data.ItemFileWriteStore({
					data:dataset,
					typeMap: customTypeMap
			});
			
			store._saveEverything = function(saveCompleteCallback, saveFailedCallback, newFileContentString){
				//Now load the new data into a datastore and validate that it stored the Color right.
				var dataset = dojo.fromJson(newFileContentString);
				var newStore = new dojo.data.ItemFileWriteStore({data: dataset, typeMap: customTypeMap});

				function gotItem(item){
					var hairColor = newStore.getValue(item,"hairColor"); 
					doh.assertTrue(hairColor instanceof dojo.Color);
					doh.assertEqual("rgba(255, 255, 0, 1)", hairColor.toString());
					saveCompleteCallback();
				}
				function failed(error, request){
					deferred.errback(error);
					saveFailedCallback();
				}
				newStore.fetchItemByIdentity({identity:"Animal", onItem:gotItem, onError:failed});
			};

			//Add a new item with a color type, then save it.
			var deferred = new doh.Deferred();
			function onError(error){
				deferred.errback(error);
			}
			function onComplete() {
				deferred.callback(true);
			}

			var animal = store.newItem({name: "Animal", hairColor: new dojo.Color("yellow")});
			store.save({onComplete:onComplete, onError:onError});
			return deferred; //Object
		},
		function testWriteAPI_saveEverything_withCustomColorTypeGeneral(){
			//	summary: 
			//		Simple test of the save API	with a non-atomic type (dojo.Color) that has a type mapping.
			//	description:
			//		Simple test of the save API	with a non-atomic type (dojo.Color) that has a type mapping.

			//Set up the store basics:  What data it has, and what to do when save is called for saveEverything
			//And how to map the 'Color' type in and out of the format.
			//(Test of saving all to a some location...)
			var dataset = {
				identifier:'name',
				items: [
					{ name:'Kermit', species:'frog', color:{_type:'Color', _value:'green'} },
					{ name:'Beaker', hairColor:{_type:'Color', _value:'red'} }
				]
			};

			var customTypeMap = {'Color': 	{	
												type: dojo.Color,
												deserialize: function(value){
													return new dojo.Color(value);
												},
												serialize: function(obj){
													return obj.toString();
												}
											}
								}
			var store = new dojo.data.ItemFileWriteStore({
					data:dataset,
					typeMap: customTypeMap
			});
			store._saveEverything = function(saveCompleteCallback, saveFailedCallback, newFileContentString){
				//Now load the new data into a datastore and validate that it stored the Color right.
				var dataset = dojo.fromJson(newFileContentString);
				var newStore = new dojo.data.ItemFileWriteStore({data: dataset, typeMap: customTypeMap});

				function gotItem(item){
					var hairColor = newStore.getValue(item,"hairColor"); 
					doh.assertTrue(hairColor instanceof dojo.Color);
					doh.assertEqual("rgba(255, 255, 0, 1)", hairColor.toString());
					saveCompleteCallback();
				}
				function failed(error, request){
					deferred.errback(error);
					saveFailedCallback();
				}
				newStore.fetchItemByIdentity({identity:"Animal", onItem:gotItem, onError:failed});
			};

			//Add a new item with a color type, then save it.
			var deferred = new doh.Deferred();
			function onError(error){
				deferred.errback(error);
			}
			function onComplete() {
				deferred.callback(true);
			}

			var animal = store.newItem({name: "Animal", hairColor: new dojo.Color("yellow")});
			store.save({onComplete:onComplete, onError:onError});
			return deferred; //Object
		},
		function testWriteAPI_newItem_revert(){
			//	summary: 
			//		Test for bug #5357.  Ensure that the revert properly nulls the identity position
			//      for a new item after revert.
			var args = {data: {
				label:"name",
				items:[
					{name:'Ecuador', capital:'Quito'},
					{name:'Egypt', capital:'Cairo'},
					{name:'El Salvador', capital:'San Salvador'},
					{name:'Equatorial Guinea', capital:'Malabo'},
					{name:'Eritrea', capital:'Asmara'},
					{name:'Estonia', capital:'Tallinn'},
					{name:'Ethiopia', capital:'Addis Ababa'}
				]
			} }; 
			var store = new dojo.data.ItemFileWriteStore(args);

			var newCountry = store.newItem({name: "Utopia", capitol: "Perfect"});

			//DO NOT ACCESS THIS WAY.  THESE ARE INTERNAL VARIABLES.  DOING THIS FOR TEST PURPOSES.
			var itemEntryNum = newCountry[store._itemNumPropName];
			doh.assertTrue(store._arrayOfAllItems[itemEntryNum] === newCountry);
			store.revert();
			doh.assertTrue(store._arrayOfAllItems[itemEntryNum] === null);
		},
		function testNotificationAPI_onSet(){
			//	summary: 
			//		Simple test of the onSet API
			//	description:
			//		Simple test of the onSet API
			var store = new dojo.data.ItemFileWriteStore(tests.data.readOnlyItemFileTestTemplates.getTestData("countries"));

			var deferred = new doh.Deferred();
			function onError(error){
				deferred.errback(error);
			}
			function onItem(fetchedItem){
				var egypt = fetchedItem;
				var connectHandle = null;
				function setValueHandler(item, attribute, oldValue, newValue){
					doh.assertTrue(store.isItem(item));
					doh.assertTrue(item == egypt);
					doh.assertTrue(attribute == "capital");
					doh.assertTrue(oldValue == "Cairo");
					doh.assertTrue(newValue == "New Cairo");
					deferred.callback(true);
					dojo.disconnect(connectHandle);
				}
				connectHandle = dojo.connect(store, "onSet", setValueHandler);
				store.setValue(egypt, "capital", "New Cairo");
			}
			store.fetchItemByIdentity({identity:"eg", onItem:onItem, onError:onError});
		},
		function testNotificationAPI_onNew(){
			//	summary: 
			//		Simple test of the onNew API
			//	description:
			//		Simple test of the onNew API
			var store = new dojo.data.ItemFileWriteStore(tests.data.readOnlyItemFileTestTemplates.getTestData("countries"));

			var deferred = new doh.Deferred();
			var connectHandle = null;
			function newItemHandler(item){
				doh.assertTrue(store.isItem(item));
				doh.assertTrue(store.getValue(item, "name") == "Canada");
				deferred.callback(true);
				dojo.disconnect(connectHandle);
			}
			connectHandle = dojo.connect(store, "onNew", newItemHandler);
			var canada = store.newItem({name:"Canada", abbr:"ca", capital:"Ottawa"});
		},
		function testNotificationAPI_onDelete(){
			//	summary: 
			//		Simple test of the onDelete API
			//	description:
			//		Simple test of the onDelete API
			var store = new dojo.data.ItemFileWriteStore(tests.data.readOnlyItemFileTestTemplates.getTestData("countries"));

			var deferred = new doh.Deferred();
			function onError(error){
				deferred.errback(error);
			}
			function onItem(fetchedItem){
				var egypt = fetchedItem;
				var connectHandle = null;
				function deleteItemHandler(item){
					doh.assertTrue(store.isItem(item) == false);
					doh.assertTrue(item == egypt);
					deferred.callback(true);
					dojo.disconnect(connectHandle);
				}
				connectHandle = dojo.connect(store, "onDelete", deleteItemHandler);
				store.deleteItem(egypt);
			}
			store.fetchItemByIdentity({identity:"eg", onItem:onItem, onError:onError});
		},
		function testReadAPI_functionConformanceToo(){
			//	summary: 
			//		Simple test read API conformance.  Checks to see all declared functions are actual functions on the instances.
			//	description:
			//		Simple test read API conformance.  Checks to see all declared functions are actual functions on the instances.
			var testStore = new dojo.data.ItemFileWriteStore(tests.data.readOnlyItemFileTestTemplates.getTestData("countries"));
			var readApi = new dojo.data.api.Read();
			var passed = true;

			for(var functionName in readApi){
				var member = readApi[functionName];
				//Check that all the 'Read' defined functions exist on the test store.
				if(typeof member === "function"){
					var testStoreMember = testStore[functionName];
					if(!(typeof testStoreMember === "function")){
						passed = false;
						break;
					}
				}
			}
			doh.assertTrue(passed);
		},
		function testWriteAPI_functionConformance(){
			//	summary: 
			//		Simple test write API conformance.  Checks to see all declared functions are actual functions on the instances.
			//	description:
			//		Simple test write API conformance.  Checks to see all declared functions are actual functions on the instances.
			var testStore = new dojo.data.ItemFileWriteStore(tests.data.readOnlyItemFileTestTemplates.getTestData("countries"));
			var writeApi = new dojo.data.api.Write();
			var passed = true;

			for(var functionName in writeApi){
				var member = writeApi[functionName];
				//Check that all the 'Write' defined functions exist on the test store.
				if(typeof member === "function"){
					var testStoreMember = testStore[functionName];
					if(!(typeof testStoreMember === "function")){
						passed = false;
						break;
					}
				}
			}
			doh.assertTrue(passed);
		},
		function testNotificationAPI_functionConformance(){
			//	summary: 
			//		Simple test Notification API conformance.  Checks to see all declared functions are actual functions on the instances.
			//	description:
			//		Simple test Notification API conformance.  Checks to see all declared functions are actual functions on the instances.
			var testStore = new dojo.data.ItemFileWriteStore(tests.data.readOnlyItemFileTestTemplates.getTestData("countries"));
			var api = new dojo.data.api.Notification();
			var passed = true;

			for(var functionName in api){
				var member = api[functionName];
				//Check that all the 'Write' defined functions exist on the test store.
				if(typeof member === "function"){
					var testStoreMember = testStore[functionName];
					if(!(typeof testStoreMember === "function")){
						passed = false;
						break;
					}
				}
			}
			doh.assertTrue(passed);
		},
		function testIdentityAPI_noIdentifierSpecified(){
			//	summary: 
			//		Test for bug #3873. Given a datafile that does not specify an
			//		identifier, make sure ItemFileWriteStore auto-creates identities 
			//		that are unique even after calls to deleteItem() and newItem()
			var args = {data: {
				label:"name",
				items:[
					{name:'Ecuador', capital:'Quito'},
					{name:'Egypt', capital:'Cairo'},
					{name:'El Salvador', capital:'San Salvador'},
					{name:'Equatorial Guinea', capital:'Malabo'},
					{name:'Eritrea', capital:'Asmara'},
					{name:'Estonia', capital:'Tallinn'},
					{name:'Ethiopia', capital:'Addis Ababa'}
				]
			} }; 
			var store = new dojo.data.ItemFileWriteStore(args);
			var deferred = new doh.Deferred();
			
			var onError = function(error, request){
				deferred.errback(error);
			}
			var onComplete = function(items, request){
				doh.assertEqual(7, items.length);
				
				var lastItem = items[(items.length - 1)];
				var idOfLastItem = store.getIdentity(lastItem);
				store.deleteItem(lastItem);
				store.newItem({name:'Canada', capital:'Ottawa'});
				
				var onCompleteAgain = function(itemsAgain, requestAgain){
					doh.assertEqual(7, itemsAgain.length);
					var identitiesInUse = {};
					for(var i = 0; i < itemsAgain.length; ++i){
						var item = itemsAgain[i];
						var id = store.getIdentity(item);
						if(identitiesInUse.hasOwnProperty(id)){
							// there should not already be an entry for this id
							doh.assertTrue(false);
						}else{
							// we want to add the entry now
							identitiesInUse[id] = item;
						}
					}
					deferred.callback(true);
				}
				store.fetch({onComplete:onCompleteAgain, onError:onError});
			}
			
			store.fetch({onComplete:onComplete, onError:onError});
			return deferred;
		},
		function testIdentityAPI_noIdentifierSpecified_revert(){
			//	summary: 
			//		Test for bug #4691  Given a datafile that does not specify an
			//		identifier, make sure ItemFileWriteStore auto-creates identities 
			//		that are unique even after calls to deleteItem() and newItem()
			var args = {data: {
				label:"name",
				items:[
					{name:'Ecuador', capital:'Quito'},
					{name:'Egypt', capital:'Cairo'},
					{name:'El Salvador', capital:'San Salvador'},
					{name:'Equatorial Guinea', capital:'Malabo'},
					{name:'Eritrea', capital:'Asmara'},
					{name:'Estonia', capital:'Tallinn'},
					{name:'Ethiopia', capital:'Addis Ababa'}
				]
			} }; 
			var store = new dojo.data.ItemFileWriteStore(args);
			var deferred = new doh.Deferred();
			
			var onError = function(error, request){
				deferred.errback(error);
			}
			var onComplete = function(items, request){
				doh.assertEqual(7, items.length);
				
				var lastItem = items[(items.length - 1)];
				var idOfLastItem = store.getIdentity(lastItem);
				store.deleteItem(lastItem);
				store.newItem({name:'Canada', capital:'Ottawa'});
				
				var onCompleteAgain = function(itemsAgain, requestAgain){
					doh.assertEqual(7, itemsAgain.length);
					var identitiesInUse = {};
					for(var i = 0; i < itemsAgain.length; ++i){
						var item = itemsAgain[i];
						var id = store.getIdentity(item);
						if(identitiesInUse.hasOwnProperty(id)){
							// there should not already be an entry for this id
							doh.assertTrue(false);
						}else{
							// we want to add the entry now
							identitiesInUse[id] = item;
						}
					}
					//Last test, revert everything and check item sizes.
					store.revert();

					//Now call fetch again and verify store state.
					var revertComplete = function(itemsReverted, request){
						doh.assertEqual(7, itemsReverted.length);
						deferred.callback(true);
					}
					store.fetch({onComplete:revertComplete, onError:onError});
				}
				store.fetch({onComplete:onCompleteAgain, onError:onError});
			}
			store.fetch({onComplete:onComplete, onError:onError});
			return deferred;
		}
	]
);



}

using CompositeClassLibrary.CompositePattern;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CompositeClassLibrary
{
    public class Composition : IComposition, IComponent
    {
        public string Name { get; set; }

        //private List<Component> components;
        private Composite _composite;

        public Composition(string name, Compositor compositor) 
        {
            _composite = new Composite(name);
            Name = name;
        }

        public void Add(IComponent component)
        {
            _composite.Insert(component);
            //_composite = (Composite)component;
        }

        public void Insert(IComponent component, int index)
        {
            throw new NotImplementedException();
        }

        public void Draw(Graphics graphics)
        {
            //components.ForEach(a => a.Draw(graphics));
            _composite.Draw(graphics);
        }

        public void Remove(IComponent component)
        {
            throw new NotImplementedException();
        }

        public IComponent GetChild(int index)
        {
            throw new NotImplementedException();
        }

        public void Compositor_Compose() // TODO: udskift med compositor - one line per objecttype
        {
            IComponent composedRoot = Compositor_GroupByTypeInComposites();
            Add(composedRoot);
        }

        private Composite Compositor_GroupByTypeInComposites()
        {
            _composite.SortComponents();

            Composite composedRoot = null;
            Composite currentLine = null;
            IComponent prevComponent = null;
            int cnt = 0;
            foreach (IComponent component in _composite)
            {
                if (prevComponent?.GetType() != component.GetType())
                {
                    if (composedRoot == null)
                    {
                        composedRoot = new Composite("ComposedRoot");
                    }
                    else
                    {
                        composedRoot.Insert(currentLine);
                    }
                    currentLine = new Composite("CompositeLine_" + cnt);
                    cnt++;
                }

                currentLine.Insert(component);
                prevComponent = component;
            }

            composedRoot.Insert(currentLine);

            return composedRoot;
        }
    }
}
